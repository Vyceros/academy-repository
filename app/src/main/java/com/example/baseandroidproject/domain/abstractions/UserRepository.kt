package com.example.baseandroidproject.domain.abstractions

import androidx.paging.PagingData
import com.example.baseandroidproject.data.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<PagingData<UserEntity>>

    suspend fun insertUser(user: UserEntity)

    suspend fun getUserByEmail(email: String): UserEntity?
}