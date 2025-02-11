package com.example.baseandroidproject.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.baseandroidproject.data.local.AppDatabase
import com.example.baseandroidproject.data.local.entities.UserEntity
import com.example.baseandroidproject.data.remote.api.UserService
import com.example.baseandroidproject.domain.mappers.toUserEntity
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val database: AppDatabase,
    private val apiService: UserService
) : RemoteMediator<Int, UserEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastUser = state.lastItemOrNull()
                    if (lastUser == null) {
                        1
                    } else {
                        (lastUser.id / state.config.pageSize) + 1
                    }
                }
            }

            val response = apiService.getUsers(loadKey, state.config.pageSize)
            val users = response.body()?.data.orEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.userDao().clearAllUsers()
                }
                database.userDao().insertUsers(users.map { it.toUserEntity() })
            }

            MediatorResult.Success(
                endOfPaginationReached = users.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
