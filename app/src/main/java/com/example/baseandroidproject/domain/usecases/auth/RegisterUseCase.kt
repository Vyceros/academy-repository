package com.example.baseandroidproject.domain.usecases.auth

import com.example.baseandroidproject.domain.abstractions.auth.AuthRegisterRepository
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerRepository: AuthRegisterRepository) {
    suspend operator fun invoke(email : String, password : String): Flow<Resource<AuthResponse>> {
        return registerRepository.registerUser(email = email, password = password)
    }
}