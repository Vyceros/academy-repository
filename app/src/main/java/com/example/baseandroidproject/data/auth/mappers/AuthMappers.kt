package com.example.baseandroidproject.data.auth.mappers

import com.example.baseandroidproject.data.auth.dto.AuthRequestDto
import com.example.baseandroidproject.data.auth.dto.AuthResponseDto
import com.example.baseandroidproject.domain.models.auth.AuthRequest
import com.example.baseandroidproject.domain.models.auth.AuthResponse


fun AuthResponseDto.toDomain() : AuthResponse{
    return AuthResponse(
        id = id,
        token = token
    )
}

fun AuthRequest.toDto() : AuthRequestDto{
    return AuthRequestDto(
        email = email,
        password = password
    )
}
