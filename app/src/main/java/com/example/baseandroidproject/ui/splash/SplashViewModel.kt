package com.example.baseandroidproject.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.local.repos.UserDetailsRepository
import com.example.baseandroidproject.data.remote.response_handler.ApiResponseHandler
import kotlinx.coroutines.launch

class SplashViewModel(private val userDetailsRepository: UserDetailsRepository) :
    ApiResponseHandler() {

    fun checkForToken(tokenCheck : (Boolean) -> Unit){
        viewModelScope.launch {
            val token = userDetailsRepository.getUserDetails()?.token
            tokenCheck(token != null)
        }
    }
}