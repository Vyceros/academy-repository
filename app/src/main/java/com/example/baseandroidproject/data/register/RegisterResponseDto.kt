package com.example.baseandroidproject.data.register

import kotlinx.serialization.Serializable

// register endpoint response, it returns two fields id and token
@Serializable
data class RegisterResponseDto(
    val id : Int,
    val token : String
)
