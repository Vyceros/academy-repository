package com.example.baseandroidproject.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id : String,
    val name : String,
    @SerialName("name_de")
    val nameGerman : String,
    val children : List<CategoryDto> = emptyList()
)
