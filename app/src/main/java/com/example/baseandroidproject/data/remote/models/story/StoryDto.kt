package com.example.baseandroidproject.data.remote.models.story

import kotlinx.serialization.Serializable


@Serializable
data class StoryDto(
    val id : Int,
    val cover : String,
    val title : String
)
