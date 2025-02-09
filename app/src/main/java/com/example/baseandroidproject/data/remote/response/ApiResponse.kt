package com.example.baseandroidproject.data.remote.response


/*this is basically a wrapper class for the api responses
* encapsulates the types of responses from the server */

sealed class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : ApiResponse<T>(data)

    class Error<T>(message: String?) : ApiResponse<T>(message = message)

    class Exception<T>(message: String?) : ApiResponse<T>(message = message)

    class Loading<T> : ApiResponse<T>()
}

fun ApiResponse<*>.isErrorMessage() = this is ApiResponse.Error
fun ApiResponse<*>.isExceptionMessage() = this is ApiResponse.Exception
fun ApiResponse<*>.isSuccessMessage() = this is ApiResponse.Success
fun ApiResponse<*>.isLoadingMessage() = this is ApiResponse.Loading