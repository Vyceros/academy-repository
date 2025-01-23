package com.example.baseandroidproject.viewModels.register

import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.client.response_handler.ApiResponseHandler
import com.example.baseandroidproject.client.retrofit.RetrofitClient
import com.example.baseandroidproject.data.register.RegisterRequest
import com.example.baseandroidproject.data.register.RegisterResponse
import com.example.baseandroidproject.data.response.ApiResponse
import com.example.baseandroidproject.helpers.Validators
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ApiResponseHandler() {
    private val _registerCall = MutableStateFlow<ApiResponse<RegisterResponse>?>(null)
    val registerCall = _registerCall.asStateFlow()

    private val validator = Validators()

    fun registerUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            handleApiCall {
                RetrofitClient.apiService.register(RegisterRequest(email, password))
            }.collect { response ->
                _registerCall.value = response
            }

        }

    }


    fun validateEmail(email: String): Boolean = validator.validateEmail(email)

    fun validatePassword(password: String): Boolean = validator.validatePassword(password)

    fun validateRepeatPassword(password: String, repeatPassword: String): Boolean =
        validator.validateRepeatPassword(password, repeatPassword)


}