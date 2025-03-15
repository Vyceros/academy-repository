package com.example.baseandroidproject.presentation.profile

sealed class ProfileEvent {
    data object Logout : ProfileEvent()
}