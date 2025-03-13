package com.example.baseandroidproject.data.repositories.auth_repository

import com.example.baseandroidproject.data.helpers.SafeCall
import com.example.baseandroidproject.data.remote.api.AuthorizationService
import com.example.baseandroidproject.data.remote.models.auth.AuthRequestDto
import com.example.baseandroidproject.data.utils.mappers.mapFlow
import com.example.baseandroidproject.data.utils.mappers.toDomain
import com.example.baseandroidproject.domain.abstractions.auth.AuthLoginRepository
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthLoginRepositoryImpl @Inject constructor(
    private val service : AuthorizationService,
    private val safeCall : SafeCall
) : AuthLoginRepository {
    override suspend fun loginUser(email : String, password : String): Flow<Resource<AuthResponse>> {
        return safeCall.apiCall {
            service.loginUser(AuthRequestDto(email, password))
        }.mapFlow { dto ->
            dto.toDomain()
        }
    }
}