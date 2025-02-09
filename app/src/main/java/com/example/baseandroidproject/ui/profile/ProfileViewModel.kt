package com.example.baseandroidproject.ui.profile

import androidx.lifecycle.ViewModel
import com.example.baseandroidproject.helpers.Validators

class ProfileViewModel() : ViewModel() {


    private val validator = Validators()

    private fun validateEmail(email: String): Boolean = validator.validateEmail(email)

    private fun validateName(name: String): Boolean = validator.validateName(name)

}
