package com.example.baseandroidproject.domain.usecases.auth

import com.example.baseandroidproject.domain.abstractions.auth.AuthLoginRepository
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthRequest
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: AuthLoginRepository) {

    suspend operator fun invoke(request: AuthRequest): Flow<Resource<AuthResponse>> {
        return loginRepository.loginUser(request)
    }
}