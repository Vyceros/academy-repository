package com.example.baseandroidproject.data.login

import kotlinx.serialization.Serializable

//login endpoint response, it returns one field token that is string type

@Serializable
data class LoginResponseDto(
    val token : String
)