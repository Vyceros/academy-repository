package com.example.baseandroidproject.domain.abstractions.user

import androidx.paging.PagingData
import com.example.baseandroidproject.domain.models.user.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<PagingData<UserResponse.User>>
}