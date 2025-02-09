package com.example.baseandroidproject.storage

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.baseandroidproject.client.services.AuthorizationService
import com.example.baseandroidproject.helpers.toUserEntity
import com.example.baseandroidproject.storage.user_list.UserEntity
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
                    if (lastUser == null) {
                        1
                    } else {
                        (lastUser.id / state.config.pageSize) + 1
                    }
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
            MediatorResult.Success(endOfPaginationReached = users.body()?.data.orEmpty().isEmpty())
        } catch (ex: IOException) {
            MediatorResult.Error(ex)
        } catch (ex: HttpException) {
            MediatorResult.Error(ex)
        }
    }

}