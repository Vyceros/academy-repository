package com.example.baseandroidproject.persistence.remote

import com.example.baseandroidproject.persistence.local.UserEntity

sealed class Resource {
    object Loading : Resource()
    data class Success(val data: List<UserEntity>) : Resource()
    data class Error(val message: String) : Resource()
}