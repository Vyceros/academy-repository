package com.example.baseandroidproject.data

sealed class Resource {
    object Default : Resource()
    data class Success(val message: String ) : Resource()
    data class Error(val message: String) : Resource()

}