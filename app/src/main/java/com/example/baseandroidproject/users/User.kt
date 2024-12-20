package com.example.baseandroidproject.users

data class User(
    var id: Int,
    val firstName: String, val lastName: String, val birthday: String,
    val address: String,
    val email: String,
)
