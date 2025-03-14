package com.example.baseandroidproject.data.auth.dto
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val id : Int? = null,
    val token : String
)