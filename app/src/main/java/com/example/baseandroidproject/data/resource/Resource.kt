package com.example.baseandroidproject.data.resource

sealed class Resource<T>(
    val data: T? = null,
    val message: String
) {
    class Loading<T>(data: T? = null) : Resource<T>(data, message = "")
    class Success<T>(data: T) : Resource<T>(data, message = "")
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}