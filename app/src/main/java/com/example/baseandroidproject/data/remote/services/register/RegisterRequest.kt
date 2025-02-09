package com.example.baseandroidproject.data.remote.services.register

import kotlinx.serialization.Serializable

/*
represents registration request that we send to the server
Both fields are required
*/

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String
)
