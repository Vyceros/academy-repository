package com.example.baseandroidproject.data.remote.services.login

import kotlinx.serialization.Serializable

// Server response after login request

@Serializable
data class LoginResponse(
    val token : String
)
