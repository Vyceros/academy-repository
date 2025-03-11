package com.example.baseandroidproject.data.remote.models.auth
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val id : Int? = null,
    val token : String
)