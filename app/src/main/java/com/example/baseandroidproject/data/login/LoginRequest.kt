package com.example.baseandroidproject.data.login

import kotlinx.serialization.Serializable

/*
represents login request that we send to the server
Both fields are required
*/

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)
