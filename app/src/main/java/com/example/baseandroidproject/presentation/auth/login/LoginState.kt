package com.example.baseandroidproject.presentation.auth.login

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data object Success : LoginState()
    data class Error(val error : String? = null) : LoginState()
}