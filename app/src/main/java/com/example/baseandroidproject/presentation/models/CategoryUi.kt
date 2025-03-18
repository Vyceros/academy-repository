package com.example.baseandroidproject.presentation.models

data class CategoryUi(
    val id : String,
    val name : String,
    val germanName : String,
    val main : String,
    val childPosition : Int = 0
)
