package com.example.baseandroidproject.domain.models

data class Category(
    val id : String,
    val name : String,
    val nameGerman : String,
    val createdAt : String,
    val bglNumber : String?,
    val bglVariant : String?,
    val orderId : Int,
    val main : String?,
    val childPosition : Int = 0,
    val children : List<Category> = emptyList()
)
