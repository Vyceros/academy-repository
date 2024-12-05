package com.example.fourthassignment

import android.util.Patterns

class ValidateInputs {

    fun validateEmptyInputs(inputStrings: List<String>) : Boolean {
        return inputStrings.any { it.isNotEmpty() }
    }

    fun validateUserName(usernameInput : String) : Boolean
    {
        return usernameInput.length >= 10
    }

    fun validateEmail(emailInput : String) : Boolean
    {
        return Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()
    }

    fun validateAge(ageInput : String) : Boolean
    {
        val ageNumberFormat = ageInput.toIntOrNull() ?: return false
        return ageNumberFormat in 0..122
    }
}