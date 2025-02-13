package com.example.baseandroidproject.data.repositories.abstractions

import com.example.baseandroidproject.data.remote.models.auth.AuthRequest
import com.example.baseandroidproject.data.remote.models.auth.AuthResponse
import com.example.baseandroidproject.data.resource.Resource
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    suspend fun loginUser(loginRequest: AuthRequest): Flow<Resource<AuthResponse>>
    suspend fun registerUser(registerRequest: AuthRequest): Flow<Resource<AuthResponse>>
}