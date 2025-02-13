package com.example.baseandroidproject.data.repositories.abstractions

import androidx.paging.PagingData
import com.example.baseandroidproject.data.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun getUsers(): Flow<PagingData<UserEntity>>

    suspend fun insertUser(user: UserEntity)

    suspend fun getUserByEmail(email: String): UserEntity?

}