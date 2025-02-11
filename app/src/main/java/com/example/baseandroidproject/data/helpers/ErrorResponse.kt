package com.example.baseandroidproject.data.helpers

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error : String
)