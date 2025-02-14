package com.example.baseandroidproject.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.baseandroidproject.data.local.AppDatabase
import com.example.baseandroidproject.data.local.entities.RemoteKeys
import com.example.baseandroidproject.data.local.entities.UserEntity
import com.example.baseandroidproject.data.remote.api.UserService
import com.example.baseandroidproject.data.utils.toUserEntity
import com.example.baseandroidproject.domain.abstractions.InternetObserver
import kotlinx.coroutines.flow.first
import okio.IOException
import retrofit2.HttpException
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val database: AppDatabase,
    private val apiService: UserService,
    private val internetConnection : InternetObserver
) : RemoteMediator<Int, UserEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        val page = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> {
                return pageKeyData
            }

            else -> {
                pageKeyData as Int
            }
        }

        val connected = internetConnection.isConnected.first()
        if(!connected){
            return MediatorResult.Success(true)
        }
        try {
            val response = apiService.getUsers(page,state.config.pageSize)
            val userEntityList = response.body()?.data?.map { it.toUserEntity() }
            val isEndOfList = response.body()?.data?.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH){
                    database.remoteKeysDao().clearRemoteKeys()
                    database.userDao().clearAllUsers()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (isEndOfList == true) null else page + 1
                val keys = userEntityList?.map {
                    RemoteKeys(id = it.id, prevKey = prevKey, nextKey = nextKey)

                }
                if (keys != null) {
                    database.remoteKeysDao().insertAll(keys)
                    database.userDao().insertUsers(userEntityList)
                }
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList == true)
        }catch (ex : IOException){
            return MediatorResult.Error(ex)
        }catch (ex :HttpException){
            return MediatorResult.Error(ex)
        }
    }
        private suspend fun getClosestRemoteKey(state: PagingState<Int, UserEntity>): RemoteKeys? {
            return state.anchorPosition?.let { position ->
                state.closestItemToPosition(position)?.id?.let { repoId ->
                    database.remoteKeysDao().remoteKeysId(repoId)
                }
            }
        }

        private suspend fun getFirstRemoteKey(state: PagingState<Int, UserEntity>): RemoteKeys? {
            return state.pages
                .firstOrNull() { it.data.isNotEmpty() }
                ?.data?.firstOrNull()
                ?.let { doggo -> database.remoteKeysDao().remoteKeysId(doggo.id) }
        }

        private suspend fun getLastRemoteKey(state: PagingState<Int, UserEntity>): RemoteKeys? {
            return state.pages
                .lastOrNull() { it.data.isNotEmpty() }
                ?.data?.lastOrNull()
                ?.let { doggo -> database.remoteKeysDao().remoteKeysId(doggo.id) }
        }

        suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, UserEntity>): Any? {
            return when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getClosestRemoteKey(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }

                LoadType.APPEND -> {
                    val remoteKeys = getLastRemoteKey(state)
                        ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                    remoteKeys.nextKey
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getFirstRemoteKey(state)
                        ?: throw InvalidObjectException("Invalid state, key should not be null")
                    remoteKeys.prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    remoteKeys.prevKey
                }
            }
        }
    }
