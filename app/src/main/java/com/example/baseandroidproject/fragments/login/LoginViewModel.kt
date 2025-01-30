package com.example.baseandroidproject.fragments.login

import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.client.response_handler.ApiResponseHandler
import com.example.baseandroidproject.client.retrofit.RetrofitClient
import com.example.baseandroidproject.data.login.LoginRequest
import com.example.baseandroidproject.data.login.LoginResponse
import com.example.baseandroidproject.data.response.ApiResponse
import com.example.baseandroidproject.helpers.Validators
import com.example.baseandroidproject.sessions.ProtoDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val dataStore: ProtoDataStore) : ApiResponseHandler() {
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

    fun saveToken(token: String, email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.updateUserDetail(token = token, email = email)
        }
    }

    private val validator = Validators()

    fun validateEmail(email: String): Boolean = validator.validateEmail(email)

    fun validatePassword(password: String): Boolean = validator.validatePassword(password)
}