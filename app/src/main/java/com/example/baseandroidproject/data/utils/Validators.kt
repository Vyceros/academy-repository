package com.example.baseandroidproject.data.utils

import android.util.Patterns

fun validateEmails(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun validatePasswords(password: String): Boolean {
    return password.isNotEmpty()
}

fun validateRepeatPasswords(password: String, repeatPassword: String): Boolean {
    return password == repeatPassword
}