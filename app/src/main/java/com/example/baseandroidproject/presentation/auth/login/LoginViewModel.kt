package com.example.baseandroidproject.presentation.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.abstractions.datastore.DataStoreRepository
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthRequest
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import com.example.baseandroidproject.domain.singletons.DataStoreKeys
import com.example.baseandroidproject.domain.usecases.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase,
    private val dataStore: DataStoreRepository
) : ViewModel() {
    private val _loginState = MutableStateFlow<Resource<AuthResponse>?>(null)
    val loginState = _loginState


    fun loginUser(
        email: String,
        password: String,
        rememberMe: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.invoke(AuthRequest(email,password)).collect { response ->

                if (response is Resource.Success && response.data != null) {
                    val token = response.data.token
                    dataStore.addPreference(DataStoreKeys.UserEmail,email)
                    dataStore.addPreference(DataStoreKeys.UserToken,token)
                    dataStore.addPreference(DataStoreKeys.RememberMe,rememberMe)
                    Log.d("RememberMe",rememberMe.toString())
                }
                _loginState.value = response
            }
        }
    }

}