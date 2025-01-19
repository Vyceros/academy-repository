package com.example.baseandroidproject.data.login

import kotlinx.serialization.Serializable

//data transfer object for login request
@Serializable
data class LoginRequestDto(
    val email : String,
    val password : String
)
