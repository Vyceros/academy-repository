package com.example.baseandroidproject.presentation.auth.login

sealed class LoginEvent {
    data object NavigateToHome : LoginEvent()
    data class ShowError(val message: String) : LoginEvent()
}
