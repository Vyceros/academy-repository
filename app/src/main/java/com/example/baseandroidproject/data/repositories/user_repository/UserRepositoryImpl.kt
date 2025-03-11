package com.example.baseandroidproject.data.repositories.user_repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.baseandroidproject.data.local.dao.UserDao
import com.example.baseandroidproject.data.local.paging.UserPaging
import com.example.baseandroidproject.data.remote.api.UserService
import com.example.baseandroidproject.data.utils.mappers.toUser
import com.example.baseandroidproject.domain.abstractions.user.UserRepository
import com.example.baseandroidproject.domain.models.user.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserService,
    private val userDao: UserDao
) : UserRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getUsers(): Flow<PagingData<UserResponse.User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 6,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            remoteMediator = UserPaging(userApi, userDao),
            pagingSourceFactory = { userDao.getUsersPagingSource() }
        ).flow.map { data ->
            data.map { entity ->
                entity.toUser()
            }
        }
    }
}
