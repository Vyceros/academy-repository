package com.example.baseandroidproject.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.remote.models.auth.AuthRequest
import com.example.baseandroidproject.data.remote.models.auth.AuthResponse
import com.example.baseandroidproject.data.repositories.auth_repository.AuthRepositoryImpl
import com.example.baseandroidproject.data.resource.Resource
import com.example.baseandroidproject.data.utils.validateEmails
import com.example.baseandroidproject.data.utils.validatePasswords
import com.example.baseandroidproject.data.utils.validateRepeatPasswords
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepositoryImpl: AuthRepositoryImpl) : ViewModel() {
    private val _registerCall = MutableStateFlow<Resource<AuthResponse>?>(null)
    val registerCall = _registerCall.asStateFlow()


    fun registerUser(email : String, password : String){
        viewModelScope.launch(Dispatchers.IO) {
            authRepositoryImpl.registerUser(AuthRequest(email = email,password)).collect { response ->
                _registerCall.value = response
            }
        }
    }

    fun validateEmail(email: String): Boolean = validateEmails(email)

    fun validatePassword(password: String): Boolean = validatePasswords(password)

    fun validateRepeatPassword(password: String, repeatPassword: String): Boolean =
        validateRepeatPasswords(password, repeatPassword)
}