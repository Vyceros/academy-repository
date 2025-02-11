package com.example.baseandroidproject.data.repositories.user_repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.baseandroidproject.data.local.AppDatabase
import com.example.baseandroidproject.data.local.entities.UserEntity
import com.example.baseandroidproject.data.paging.RemoteMediator
import com.example.baseandroidproject.data.remote.api.UserService
import com.example.baseandroidproject.data.repositories.abstractions.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userService: UserService,private val database: AppDatabase) : IUserRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getUsers(): Flow<PagingData<UserEntity>> {
        val pagingSource = { database.userDao().getAllUsers() }

        return Pager(
            config = PagingConfig(
                pageSize = 6,
                initialLoadSize = 6,
                prefetchDistance = 1,
                enablePlaceholders = false,
                maxSize = 12
            ),
            remoteMediator = RemoteMediator(userService,database),
            pagingSourceFactory = pagingSource
        ).flow
    }

}