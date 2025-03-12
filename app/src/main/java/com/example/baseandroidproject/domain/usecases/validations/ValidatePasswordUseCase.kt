package com.example.baseandroidproject.domain.usecases.validations

class ValidatePasswordUseCase {
    operator fun invoke(password: String): Boolean {
        if (password.isBlank() || password.length <= 9) {
            return false
        }

        val containsLettersAndDigits =
            password.any { it.isLetter() } && password.any { it.isDigit() }

        return containsLettersAndDigits
    }

}