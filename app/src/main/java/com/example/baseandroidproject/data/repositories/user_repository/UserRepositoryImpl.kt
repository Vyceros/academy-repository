package com.example.baseandroidproject.data.repositories.user_repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.baseandroidproject.data.local.AppDatabase
import com.example.baseandroidproject.data.local.entities.UserEntity
import com.example.baseandroidproject.data.paging.UserRemoteMediator
import com.example.baseandroidproject.data.remote.api.UserService
import com.example.baseandroidproject.domain.abstractions.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val database: AppDatabase
) :
    UserRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getUsers(): Flow<PagingData<UserEntity>> {

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 6,
                prefetchDistance = 1,
                enablePlaceholders = false
            ),
            remoteMediator = UserRemoteMediator(database = database, apiService = userService),
            pagingSourceFactory = { database.userDao().getAllUsers() }
        ).flow
    }

    override suspend fun insertUser(user: UserEntity) {
        database.userDao().insertOneUser(user)
    }

    override suspend fun getUserByEmail(email: String): UserEntity? {
        return database.userDao().getUserById(email)
    }

}