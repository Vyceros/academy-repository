package com.example.baseandroidproject.data.responses

sealed class ApiResponse<T>(
    val data: T? = null,
    val error : String? = null
) {
    class Success<T>(data: T) : ApiResponse<T>(data)

    class Error<T>(errorMessage: String?) :
        ApiResponse<T>(error = errorMessage)
}
