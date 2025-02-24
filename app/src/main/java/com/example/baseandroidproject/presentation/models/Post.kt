package com.example.baseandroidproject.presentation.models

data class Post(
    val id: Int,
    val images: List<String>,
    val title: String,
    val comments: String,
    val likes: String,
    val shareContent: String,
    val fullName: String,
    val postDate: String,
    val profilePicture : String? = null
)
