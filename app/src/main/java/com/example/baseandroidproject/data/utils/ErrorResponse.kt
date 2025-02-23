package com.example.baseandroidproject.data.utils

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error : String
)
