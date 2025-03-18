package com.example.baseandroidproject.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id : String,
    val name : String,
    @SerialName("name_de")
    val nameGerman : String,
    val createdAt : String,
    @SerialName("bgl_number")
    val bglNumber : String?,
    @SerialName("bgl_variant")
    val bglVariant : String?,
    @SerialName("order_id")
    val orderId : Int?,
    val main : String?,
    val children : List<CategoryDto> = emptyList()
)
