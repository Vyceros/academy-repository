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
class RemoteMediator(
    private val apiService: UserService,
    private val database: AppDatabase
) : RemoteMediator<Int, UserEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            val page: Int = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastUser = state.lastItemOrNull()
                    if (lastUser == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    } else {
                        val nextPage = lastUser.id?.let {
                            val currentPage = (it - 1) / state.config.pageSize + 1
                            currentPage + 1
                        } ?: 1
                        nextPage
                    }
                }
            }
            val response = apiService.getUsers(page, state.config.pageSize)

            database.withTransaction {
                response.body()?.data?.let { list ->
                    database.userDao().insertUsers(list.map { it.toUserEntity() })
                }
            }
            MediatorResult.Success(
                endOfPaginationReached = response.body()?.data.orEmpty().isEmpty()
            )
        } catch (ex: IOException) {
            MediatorResult.Error(ex)
        } catch (ex: HttpException) {
            MediatorResult.Error(ex)
        } catch (ex: Throwable) {
            MediatorResult.Error(ex)
        }
    }
}