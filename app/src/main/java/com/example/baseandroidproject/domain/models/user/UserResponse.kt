package com.example.baseandroidproject.domain.models.user

data class UserResponse(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: List<User>,
){
    data class User(
        val id: Int,
        val email: String?,
        val firstName: String?,
        val lastName: String?,
        val avatar: String?
    )
}


