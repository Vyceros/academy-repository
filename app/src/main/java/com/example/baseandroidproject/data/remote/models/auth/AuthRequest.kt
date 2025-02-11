package com.example.baseandroidproject.data.remote.models.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val email : String,
    val password : String
)
