package com.example.baseandroidproject.helpers

import android.util.Patterns

class Validators{

    fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validatePassword(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun validateName(name: String): Boolean {
        return name.isNotEmpty()
    }

    fun validateRepeatPassword(password: String, repeatPassword: String): Boolean {
        return password == repeatPassword
    }

}
