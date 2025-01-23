package com.example.baseandroidproject.viewModels.login

import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.client.response_handler.ApiResponseHandler
import com.example.baseandroidproject.client.retrofit.RetrofitClient
import com.example.baseandroidproject.data.login.LoginRequest
import com.example.baseandroidproject.data.login.LoginResponse
import com.example.baseandroidproject.data.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ApiResponseHandler() {
    private val _loginCall = MutableStateFlow<ApiResponse<LoginResponse>?>(null)
    val loginCall = _loginCall.asStateFlow()

    fun loginUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            handleApiCall {
                RetrofitClient.apiService.login(LoginRequest(email, password))
            }.collect { response ->
                _loginCall.value = response
            }
        }
    }


}