package com.example.baseandroidproject.data.remote.models.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequestDto(
    val email : String,
    val password : String
)
