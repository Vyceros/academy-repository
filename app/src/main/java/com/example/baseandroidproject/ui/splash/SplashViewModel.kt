package com.example.baseandroidproject.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.client.response_handler.ApiResponseHandler
import com.example.baseandroidproject.data.repositories.UserDetailsRepository
import kotlinx.coroutines.launch

class SplashViewModel(private val userDetailsRepository: UserDetailsRepository) :
    ApiResponseHandler() {

    fun checkForToken(tokenCheck : (Boolean) -> Unit){
        viewModelScope.launch {
            val token = userDetailsRepository.getUserDetails()
            tokenCheck(token != null)
        }
    }
}