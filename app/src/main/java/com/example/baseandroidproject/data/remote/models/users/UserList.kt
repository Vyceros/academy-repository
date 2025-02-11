package com.example.baseandroidproject.data.remote.models.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserList(
    val page : Int,
    @SerialName("per_page")
    val perPage : Int,
    val total : Int,
    @SerialName("total_pages")
    val totalPages : Int,
    val data : List<UserDto>
)
