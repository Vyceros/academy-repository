package com.example.baseandroidproject.domain.abstractions.auth

import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthRequest
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import kotlinx.coroutines.flow.Flow

interface AuthLoginRepository {
    suspend fun loginUser(request: AuthRequest): Flow<Resource<AuthResponse>>
}