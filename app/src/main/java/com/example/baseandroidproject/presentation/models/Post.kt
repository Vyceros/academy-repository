package com.example.baseandroidproject.presentation.models

import kotlinx.serialization.Serializable

data class Post(
    val id: Int,
    val images: List<String>,
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: Owner,
    val postDate: String
) {
    @Serializable
    data class Owner(
        val firstName: String,
        val lastName: String
    )
}

