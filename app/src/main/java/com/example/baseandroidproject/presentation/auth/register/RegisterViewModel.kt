package com.example.baseandroidproject.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthRequest
import com.example.baseandroidproject.domain.usecases.auth.RegisterUseCase
import com.example.baseandroidproject.domain.usecases.validations.ValidateEmailUseCase
import com.example.baseandroidproject.domain.usecases.validations.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCase: RegisterUseCase,
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase) : ViewModel() {

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState = _registerState.asStateFlow()

    private val _validationState = MutableStateFlow(false)
    val validationState = _validationState.asStateFlow()

    private val _registerEvent = Channel<RegisterEvent>()
    val registerEvent = _registerEvent.receiveAsFlow()


    fun register(email : String,password : String){
        viewModelScope.launch(Dispatchers.IO) {
            useCase(AuthRequest(email = email,password = password)).onStart {
                _registerState.value = RegisterState.Loading
            }.catch {
                _registerState.value = RegisterState.Error()
            }.collect{result ->
                _registerState.value = when(result){
                    is Resource.Error -> {
                        _registerEvent.send(RegisterEvent.ShowError(result.message))
                        RegisterState.Error(result.message)
                    }
                    is Resource.Loading -> {
                        RegisterState.Loading
                    }

                    is Resource.Success -> {
                        _registerEvent.send(RegisterEvent.NavigateToLogin)
                        RegisterState.Success
                    }
                }
            }
        }
    }

    fun validateInputs(email : String,password: String){
        when(val successfulValidation = validateEmail(email) || validatePassword(password)){
            true -> {
                _validationState.value = true
            }
            false -> {
                _validationState.value = true
            }
        }
    }

}