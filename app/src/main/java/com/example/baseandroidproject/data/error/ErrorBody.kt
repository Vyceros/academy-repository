package com.example.baseandroidproject.data.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorBody(
    @SerialName("error")
    val message: String? = null
)