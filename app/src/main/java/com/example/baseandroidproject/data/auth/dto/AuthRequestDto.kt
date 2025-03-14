package com.example.baseandroidproject.data.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequestDto(
    val email : String,
    val password : String
)
