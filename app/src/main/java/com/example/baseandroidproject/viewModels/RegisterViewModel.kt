package com.example.baseandroidproject.viewModels

import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.base.ResponseRepository
import com.example.baseandroidproject.client.RetrofitClient
import com.example.baseandroidproject.data.register.RegisterRequestDto
import com.example.baseandroidproject.data.register.RegisterResponseDto
import com.example.baseandroidproject.data.responses.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ResponseRepository() {
        private val _registerCall = MutableStateFlow<ApiResponse<RegisterResponseDto>?>(null)
        val registerCall = _registerCall.asStateFlow()

        fun register(email : String, password : String){
            viewModelScope.launch(Dispatchers.IO) {
                val response = apiCall {
                    RetrofitClient.authorizationService.registerUser(RegisterRequestDto(email, password))
                }
                _registerCall.value = response
            }
        }
}