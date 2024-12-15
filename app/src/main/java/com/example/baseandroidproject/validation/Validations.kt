package com.example.baseandroidproject.validation

import android.util.Patterns

fun validateEmail(email : String) : Boolean{
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun validatePassword(password : String?) : Boolean{
    return when{
        password.isNullOrBlank() -> false
        password.length <= 8 -> false
        else -> {
            return true
        }
    }
}