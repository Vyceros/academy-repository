package com.example.baseandroidproject.data.auth.repositories

import com.example.baseandroidproject.data.auth.mappers.mapFlow
import com.example.baseandroidproject.data.auth.mappers.toDomain
import com.example.baseandroidproject.data.auth.mappers.toDto
import com.example.baseandroidproject.data.auth.service.AuthorizationService
import com.example.baseandroidproject.data.helpers.SafeCall
import com.example.baseandroidproject.domain.abstractions.auth.AuthLoginRepository
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthRequest
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthLoginRepositoryImpl @Inject constructor(
    private val service : AuthorizationService,
    private val safeCall : SafeCall
) : AuthLoginRepository {
    override suspend fun loginUser(request: AuthRequest): Flow<Resource<AuthResponse>> {
        return safeCall.apiCall {
            service.loginUser(request.toDto())
        }.mapFlow { dto ->
            dto.toDomain()
        }
    }
}