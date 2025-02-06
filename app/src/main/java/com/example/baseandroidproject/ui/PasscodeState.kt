package com.example.baseandroidproject.ui

data class PasscodeState(
    val currentInput: List<String?> = emptyList(),
    val maxLength: Int = 4,
    val isFilledOut: Boolean = false
)
