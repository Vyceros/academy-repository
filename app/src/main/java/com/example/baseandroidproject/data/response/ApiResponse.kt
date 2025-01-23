package com.example.baseandroidproject.data.response


/*this is basically a wrapper class for the api responses
* encapsulates the types of possible network responses from the server */

sealed class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : ApiResponse<T>(data)

    class Error<T>(val code: Int,message : String?) : ApiResponse<T>(message = message)

    class Exception<T>(message: String?) : ApiResponse<T>(message = message)
}