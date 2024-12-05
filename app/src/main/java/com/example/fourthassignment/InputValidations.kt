package com.example.fourthassignment

import android.util.Patterns

class InputValidations {

    // minimum 10 chat long
    fun isUserNameValid(username: String): Boolean {
        return username.length < 10
    }

    //reg expression

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validateAllInputs(editTextList: List<String>): Boolean {
        for (input in editTextList) {
            if (!isInputValid(input)) return false
        }
        return true
    }

    private fun isInputValid(inputText: String) = inputText.isNotBlank()


}
