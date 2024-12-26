package com.example.baseandroidproject.data

data class Address(
    val id: Int,
    val icon: Int,
    val title: String,
    val address: String,
    val isSelected: Boolean = false
)