package com.example.baseandroidproject.viewModels

import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.base.ResponseRepository
import com.example.baseandroidproject.client.RetrofitClient
import com.example.baseandroidproject.data.login.LoginRequestDto
import com.example.baseandroidproject.data.login.LoginResponseDto
import com.example.baseandroidproject.data.responses.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ResponseRepository() {

    private val _loginCall = MutableStateFlow<ApiResponse<LoginResponseDto>?>(null)
    val loginCall = _loginCall.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiCall {
                RetrofitClient.authorizationService.loginUser(LoginRequestDto(email, password))
            }
            _loginCall.value = response
        }
    }
}