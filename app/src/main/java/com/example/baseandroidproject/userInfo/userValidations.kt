package com.example.baseandroidproject.userInfo

import android.util.Patterns

fun validateEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun validateName(firstName: String, lastName: String): Boolean {
    return when {
        firstName.isBlank() && lastName.isBlank() -> false
        firstName.length < 3 && lastName.length < 6 -> false
        !firstName.all { it.isLetter() } && !lastName.all { it.isLetter() } -> false
        else -> {
            true
        }
    }
}

fun validateAge(age: String): Boolean {
    return when {
        age.isBlank() -> false
        age.toInt() < 0 -> false
        else -> {
            true
        }
    }
}