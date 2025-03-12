package com.example.baseandroidproject.presentation.auth.register

sealed class RegisterEvent {
    data object NavigateToLogin : RegisterEvent()
    data class ShowError(val message: String) : RegisterEvent()
}