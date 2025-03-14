package com.example.baseandroidproject.data.user.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    val data: List<UserDto>,
){
    @Serializable
    data class UserDto(
        val id: Int,
        val email: String?,
        val firstName: String?,
        val lastName: String?,
        val avatar: String?
    )
}
