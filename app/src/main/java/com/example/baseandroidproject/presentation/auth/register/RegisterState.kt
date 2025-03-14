package com.example.baseandroidproject.presentation.auth.register


sealed class RegisterState{
    data object Loading : RegisterState()
    data object Idle : RegisterState()
    data object Success : RegisterState()
    data class Error(val string : String? = null) : RegisterState()
}
