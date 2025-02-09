package com.example.baseandroidproject.ui.login

import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.remote.response_handler.ApiResponseHandler
import com.example.baseandroidproject.data.remote.retrofit.RetrofitClient
import com.example.baseandroidproject.data.remote.services.login.LoginRequest
import com.example.baseandroidproject.data.remote.services.login.LoginResponse
import com.example.baseandroidproject.data.local.repos.UserDetailsRepository
import com.example.baseandroidproject.data.remote.response.ApiResponse
import com.example.baseandroidproject.data.remote.response.isSuccessMessage
import com.example.baseandroidproject.helpers.Validators
import com.example.baseandroidproject.data.local.storage.user_details.UserDetailsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val userDetailsRepository: UserDetailsRepository) :
    ApiResponseHandler() {
    private val _loginCall = MutableStateFlow<ApiResponse<LoginResponse>?>(null)
    val loginCall = _loginCall.asStateFlow()

    fun loginUser(email: String, password: String, rememberMe: Boolean,firstName: String,lastName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            handleApiCall {
                RetrofitClient.apiService.login(LoginRequest(email, password))
            }.collect { response ->
                _loginCall.value = response

                if (response.isSuccessMessage()) {
                    val token = response.data?.token
                    token?.let {
                        saveUserDetails(token = token, firstName = firstName, lastName = lastName, email = email, rememberMe)
                    }
                }
            }
        }
    }

    private val validator = Validators()

    fun validateEmail(email: String): Boolean = validator.validateEmail(email)

    fun validatePassword(password: String): Boolean = validator.validatePassword(password)

    private suspend fun saveUserDetails(
        token: String,
        firstName: String,
        lastName: String,
        email: String,
        rememberMe: Boolean
    ) {
        val userDetails = UserDetailsEntity(
            token = if (rememberMe) token else null,
            firstName = firstName,
            lastName = lastName,
            email = email

        )
        userDetailsRepository.insertUserDetails(userDetails)
    }

}