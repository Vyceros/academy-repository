package com.example.baseandroidproject.data.register

import kotlinx.serialization.Serializable

//data transfer object for register request

@Serializable
data class RegisterRequestDto(
    val email : String,
    val password : String
)