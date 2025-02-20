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
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val initialPage: Int = 1,
    private val database: AppDatabase,
    private val apiService: UserService
) : RemoteMediator<Int, UserEntity>() {

    private val userDao = database.userDao()
    private val remoteKeysDao = database.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.APPEND -> {
                    val remoteKeys = getLastKey(state)
                    remoteKeys?.nextKey ?: return MediatorResult.Success(true)
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.REFRESH -> {
                    val remoteKeys = getClosetKey(state)
                    remoteKeys?.nextKey?.minus(1) ?: initialPage
                }
            }

            val response = apiService.getUsers(page = page, perPage = state.config.pageSize)
            val endOfPaginationReached = response.body()?.data?.size!! < state.config.pageSize

            when {
                response.isSuccessful -> {
                    val users = response.body()?.data

                    database.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            remoteKeysDao.clearRemoteKeys()
                            userDao.clearAllUsers()
                        }

                        val prevKey = if (page == initialPage) null else page - 1
                        val nextKey = if (endOfPaginationReached) null else page + 1

                        val keys = users?.map { user ->
                            RemoteKeys(
                                id = user.id,
                                prevKey = prevKey,
                                nextKey = nextKey
                            )
                        }

                        keys?.let { remoteKeysDao.insertAll(it) }
                        users?.let { userDao.insertUsers(it.map { it.toUserEntity() }) }
                    }

                    MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
                }
                else -> {
                    MediatorResult.Error(HttpException(response))
                }
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getLastKey(state: PagingState<Int, UserEntity>): RemoteKeys? {
        return state.lastItemOrNull()?.let {
            remoteKeysDao.remoteKeysId(it.id)
        }
    }

    private suspend fun getClosetKey(state: PagingState<Int, UserEntity>): RemoteKeys? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let { user ->
                remoteKeysDao.remoteKeysId(user.id)
            }
        }
    }
}



