package com.example.baseandroidproject.domain.abstractions.auth

import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import kotlinx.coroutines.flow.Flow

interface AuthRegisterRepository {
    suspend fun registerUser(email : String, password : String): Flow<Resource<AuthResponse>>

}