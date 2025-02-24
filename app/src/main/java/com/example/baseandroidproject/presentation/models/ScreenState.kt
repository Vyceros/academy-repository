package com.example.baseandroidproject.presentation.models

data class ScreenState(
    var stories: List<Story> = emptyList(),
    val posts: List<Post> = emptyList(),
    val isLoading : Boolean = true
)