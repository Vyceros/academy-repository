package com.example.baseandroidproject.data.auth.repositories

import com.example.baseandroidproject.data.auth.mappers.mapFlow
import com.example.baseandroidproject.data.auth.mappers.toDomain
import com.example.baseandroidproject.data.auth.mappers.toDto
import com.example.baseandroidproject.data.auth.service.AuthorizationService
import com.example.baseandroidproject.data.helpers.SafeCall
import com.example.baseandroidproject.domain.abstractions.auth.AuthRegisterRepository
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthRequest
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRegisterRepositoryImpl @Inject constructor(
    private val service : AuthorizationService,
    private val safeCall : SafeCall
) : AuthRegisterRepository {
    override suspend fun registerUser(request : AuthRequest): Flow<Resource<AuthResponse>> {
        return safeCall.apiCall {
            service.registerUser(request.toDto())
        }.mapFlow { dto ->
            dto.toDomain()
        }
    }
}