package com.example.baseandroidproject.data.local.storage

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.baseandroidproject.data.local.storage.user_list.UserEntity
import com.example.baseandroidproject.data.remote.services.AuthorizationService
import com.example.baseandroidproject.helpers.toUserEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val apiSource: AuthorizationService,
    private val database: ApplicationDatabase
) : RemoteMediator<Int, UserEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {

            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val lastUser = state.lastItemOrNull()
                    val lastPage = lastUser?.id ?: 0
                    (lastPage / state.config.pageSize) + 1
                }
            }

            val users = apiSource.getUsers(page = loadKey, perPage = state.config.pageSize)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.userDao().clearAll()
                }
                val userEntities = users.body()?.data?.map { it.toUserEntity() } ?: emptyList()
                database.userDao().insertAll(userEntities)
            }

            val endOfPaginationReached =
                users.body()?.data.isNullOrEmpty() || (users.body()?.data?.size
                    ?: 0) < state.config.pageSize
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (ex: IOException) {
            MediatorResult.Error(ex)
        } catch (ex: HttpException) {
            MediatorResult.Error(ex)
        }
    }
}


