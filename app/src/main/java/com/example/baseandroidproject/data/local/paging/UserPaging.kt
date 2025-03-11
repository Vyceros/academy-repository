package com.example.baseandroidproject.data.local.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.baseandroidproject.data.local.dao.UserDao
import com.example.baseandroidproject.data.local.entities.UserEntity
import com.example.baseandroidproject.data.remote.api.UserService
import com.example.baseandroidproject.data.utils.mappers.toUserEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserPaging @Inject constructor(
    private val userService: UserService,
    private val userDao: UserDao
) : RemoteMediator<Int, UserEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        try {
            val key = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastUser = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    (lastUser.id / state.config.pageSize) + 1
                }
            }

            val response = userService.getUsers(page = key, perPage = state.config.pageSize)
            if (loadType == LoadType.REFRESH) {
                userDao.clearAllUsers()
            }
            val users = response.data.map { it.toUserEntity() }
            userDao.insertAll(users)

            return MediatorResult.Success(
                endOfPaginationReached = response.data.isEmpty() ||
                        response.page >= response.totalPages
            )
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
}