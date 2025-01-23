package com.example.baseandroidproject.data.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val id : Int,
    val token : String
)
