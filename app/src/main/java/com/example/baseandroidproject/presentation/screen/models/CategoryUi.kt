package com.example.baseandroidproject.presentation.screen.models

data class CategoryUi(
    val id : String,
    val name : String,
    val children : List<CategoryIndicator>
)
