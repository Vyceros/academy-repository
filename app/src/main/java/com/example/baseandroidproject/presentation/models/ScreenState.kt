package com.example.baseandroidproject.presentation.models

data class ScreenState(
    var isLoading: Boolean = false,
    val markers : List<Location> = emptyList()
)
