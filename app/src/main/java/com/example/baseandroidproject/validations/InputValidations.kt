package com.example.baseandroidproject.validations

import android.util.Patterns
import androidx.core.text.isDigitsOnly

class InputValidations {
    fun validateEmail(email: String?): Boolean {
        return email?.trim()?.let { Patterns.EMAIL_ADDRESS.matcher(it).matches() } ?: false
    }

    fun validateUserName(userName: String?): Boolean {
        return when {
            userName.isNullOrEmpty() -> false
            userName.length < 10 -> false
            userName.any { it.isDigit() } -> false
            else -> {
                true
            }
        }
    }

    fun validateAge(age: String?): Boolean {
        return when {
            age.isNullOrEmpty() -> false
            age.toIntOrNull() !in 0..122 -> false
            !age.isDigitsOnly() -> false
            else -> {
                true
            }
        }
    }

}