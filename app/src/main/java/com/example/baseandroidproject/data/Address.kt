package com.example.baseandroidproject.data

data class Address(
    val id: Int,
    val icon: Int,
    var title: String,
    var address: String,
    var isSelected: Boolean = false
)