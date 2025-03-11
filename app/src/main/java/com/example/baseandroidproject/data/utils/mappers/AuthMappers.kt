package com.example.baseandroidproject.data.utils.mappers

import com.example.baseandroidproject.data.remote.models.auth.AuthRequestDto
import com.example.baseandroidproject.data.remote.models.auth.AuthResponseDto
import com.example.baseandroidproject.domain.models.auth.AuthRequest
import com.example.baseandroidproject.domain.models.auth.AuthResponse


fun AuthRequest.toData() : AuthRequestDto {
    return AuthRequestDto(
        email = email,
        password = password
    )
}

fun AuthResponseDto.toAuthResponse(): AuthResponse {
    return AuthResponse(
        token = this.token,
        id = id
    )
}