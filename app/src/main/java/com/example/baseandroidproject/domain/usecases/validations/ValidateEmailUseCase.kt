package com.example.baseandroidproject.domain.usecases.validations

class ValidateEmailUseCase {

    operator fun invoke(email : String) : Boolean {
        if(email.isBlank()){
            return false
        }
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"
        return email.matches(regex.toRegex())
    }
}