package com.example.baseandroidproject.presentation.auth.register


sealed class RegisterState{
    data object Loading : RegisterState()
    data object Idle : RegisterState()
    data class Success(val data : Int) : RegisterState()
}
