package com.example.baseandroidproject.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.remote.models.auth.AuthRequest
import com.example.baseandroidproject.data.remote.models.auth.AuthResponse
import com.example.baseandroidproject.data.repositories.auth_repository.AuthRepository
import com.example.baseandroidproject.data.resource.Resource
import com.example.baseandroidproject.domain.utils.validateEmails
import com.example.baseandroidproject.domain.utils.validatePasswords
import com.example.baseandroidproject.domain.utils.validateRepeatPasswords
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _registerCall = MutableStateFlow<Resource<AuthResponse>?>(null)
    val registerCall = _registerCall.asStateFlow()


    fun registerUser(email : String, password : String){
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.registerUser(AuthRequest(email = email,password)).collect { response ->
                _registerCall.value = response
                Log.d("response","${response.message}")
            }
        }
    }

    fun validateEmail(email: String): Boolean = validateEmails(email)

    fun validatePassword(password: String): Boolean = validatePasswords(password)

    fun validateRepeatPassword(password: String, repeatPassword: String): Boolean =
        validateRepeatPasswords(password, repeatPassword)
}