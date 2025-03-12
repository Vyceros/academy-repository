package com.example.baseandroidproject.presentation.models

data class AuthRequestUi(
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false
)

