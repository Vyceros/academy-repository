package com.example.baseandroidproject.ui.login

import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.client.response_handler.ApiResponseHandler
import com.example.baseandroidproject.client.retrofit.RetrofitClient
import com.example.baseandroidproject.data.login.LoginRequest
import com.example.baseandroidproject.data.login.LoginResponse
import com.example.baseandroidproject.data.repositories.UserDetailsRepository
import com.example.baseandroidproject.data.response.ApiResponse
import com.example.baseandroidproject.data.response.isSuccessMessage
import com.example.baseandroidproject.helpers.Validators
import com.example.baseandroidproject.storage.user_details.UserDetailsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val userDetailsRepository: UserDetailsRepository) : ApiResponseHandler() {
    private val _loginCall = MutableStateFlow<ApiResponse<LoginResponse>?>(null)
    val loginCall = _loginCall.asStateFlow()

    fun loginUser(email: String, password: String,rememberMe: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            handleApiCall {
                RetrofitClient.apiService.login(LoginRequest(email, password))
            }.collect { response ->
                _loginCall.value = response

                if (response.isSuccessMessage()){
                    val token = response.data?.token
                    token?.let {
                        saveUserDetails(it,rememberMe)
                    }
                }
            }
        }
    }
    private val validator = Validators()

    fun validateEmail(email: String): Boolean = validator.validateEmail(email)

    fun validatePassword(password: String): Boolean = validator.validatePassword(password)

    private suspend fun saveUserDetails(token: String, rememberMe: Boolean) {
        val userDetails = UserDetailsEntity(
            token = if (rememberMe) token else null
        )
        userDetailsRepository.insertUserDetails(userDetails)
    }

}