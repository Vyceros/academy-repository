package com.example.baseandroidproject.presentation.splash

sealed class NavigationEvent {
    data object LoginScreen : NavigationEvent()
    data object HomeScreen : NavigationEvent()
}