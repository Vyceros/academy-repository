package com.example.baseandroidproject.data.repositories.auth_repository

import com.example.baseandroidproject.data.helpers.ApiResponseHandler
import com.example.baseandroidproject.data.remote.api.AuthorizationService
import com.example.baseandroidproject.data.remote.models.auth.AuthRequest
import com.example.baseandroidproject.data.remote.models.auth.AuthResponse
import com.example.baseandroidproject.data.repositories.abstractions.IAuthRepository
import com.example.baseandroidproject.data.resource.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authorizationService: AuthorizationService,
    private val apiResponseHandler: ApiResponseHandler
) : IAuthRepository {
    override suspend fun loginUser(loginRequest: AuthRequest): Flow<Resource<AuthResponse>> {
        return apiResponseHandler.apiCall {
            authorizationService.loginUser(loginRequest)
        }
    }

    override suspend fun registerUser(registerRequest: AuthRequest): Flow<Resource<AuthResponse>> {
        return apiResponseHandler.apiCall {
            authorizationService.registerUser(registerRequest)
        }
    }


}