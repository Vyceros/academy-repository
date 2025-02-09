package com.example.baseandroidproject.data.remote.services.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val id : Int,
    val token : String
)
