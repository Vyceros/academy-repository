package com.example.baseandroidproject.data.error

import kotlinx.serialization.Serializable

//Error response that we get from the server, in case of error in our request
@Serializable
data class ErrorResponse(
    val error : String
)
