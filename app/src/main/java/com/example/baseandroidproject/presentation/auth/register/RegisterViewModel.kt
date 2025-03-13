package com.example.baseandroidproject.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import com.example.baseandroidproject.domain.usecases.auth.RegisterUseCase
import com.example.baseandroidproject.domain.usecases.validations.ValidateEmailUseCase
import com.example.baseandroidproject.domain.usecases.validations.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCase: RegisterUseCase,
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase) : ViewModel() {

    private val _registerState = MutableStateFlow<Resource<AuthResponse>?>(null)
    val registerState = _registerState.asStateFlow()

    private val _registerEvent = Channel<RegisterEvent>()
    val registerEvent = _registerEvent.receiveAsFlow()

    fun validateAndRegister(email : String, password : String) {
        viewModelScope.launch {
            val isEmailValid = validateEmail(email)
            val isPasswordValid = validatePassword(password)

            when {
                !isEmailValid -> {
                    _registerEvent.send(RegisterEvent.ShowError("Enter valid email"))
                }
                !isPasswordValid -> {
                    _registerEvent.send(RegisterEvent.ShowError("Passowrd must be 8 characters and must have both digits and letters"))
                }
                else -> {
                    registerUser(email,password)
                }
            }
        }
    }

    private fun registerUser(email : String, password : String) {

        viewModelScope.launch(Dispatchers.IO) {
            useCase.invoke(email, password).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _registerEvent.send(RegisterEvent.NavigateToLogin)
                    }
                    is Resource.Error -> {
                        _registerEvent.send(RegisterEvent.ShowError(response.message))
                    }
                    is Resource.Loading -> {
                    }
                }
                _registerState.value = response
            }
        }
    }

}