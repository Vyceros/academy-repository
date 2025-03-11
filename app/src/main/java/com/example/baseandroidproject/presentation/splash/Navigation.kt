package com.example.baseandroidproject.presentation.splash

sealed class Navigation {
    data object Idle : Navigation()
    data object LoginScreen : Navigation()
    data object HomeScreen : Navigation()
}