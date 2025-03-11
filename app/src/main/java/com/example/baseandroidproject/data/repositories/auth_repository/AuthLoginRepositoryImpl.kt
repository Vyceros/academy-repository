package com.example.baseandroidproject.data.repositories.auth_repository

import com.example.baseandroidproject.data.helpers.SafeCall
import com.example.baseandroidproject.data.remote.api.AuthorizationService
import com.example.baseandroidproject.data.utils.mappers.toAuthResponse
import com.example.baseandroidproject.data.utils.mappers.toData
import com.example.baseandroidproject.domain.abstractions.auth.AuthLoginRepository
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthRequest
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthLoginRepositoryImpl @Inject constructor(
    private val service : AuthorizationService,
    private val safeCall : SafeCall
) : AuthLoginRepository {
    override suspend fun loginUser(loginRequest: AuthRequest): Flow<Resource<AuthResponse>> {
        val request = loginRequest.toData()
        return safeCall.apiCall {
            service.loginUser(request)
        }.map { res ->
            when(res) {
                is Resource.Error -> Resource.Error(message = res.message)
                is Resource.Loading -> Resource.Loading()
                is Resource.Success -> Resource.Success(res.data!!.toAuthResponse())
            }

        }
    }
}