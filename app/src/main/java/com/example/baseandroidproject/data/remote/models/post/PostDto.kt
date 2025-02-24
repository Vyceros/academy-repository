package com.example.baseandroidproject.data.remote.models.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    val id: Int,
    val images: List<String>? = emptyList(),
    val title: String,
    val comments: Int,
    val likes: Int,
    @SerialName("share_content")
    val shareContent: String,
    val owner: Owner,
    )
{
    @Serializable
    data class Owner(
        @SerialName("first_name")
        val firstName: String,
        @SerialName("last_name")
        val lastName: String,
        @SerialName("post_date")
        val postDate : Long? = null,
        val profile : String? = null
    )
}
