package com.example.baseandroidproject.data.repositories.abstractions

import com.example.baseandroidproject.data.remote.models.auth.AuthRequest
import com.example.baseandroidproject.data.remote.models.auth.AuthResponse
import com.example.baseandroidproject.data.resource.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body

interface IAuthRepository {
    suspend fun loginUser(@Body loginRequest: AuthRequest): Flow<Resource<AuthResponse>>
    suspend fun registerUser(@Body registerRequest: AuthRequest): Flow<Resource<AuthResponse>>
}