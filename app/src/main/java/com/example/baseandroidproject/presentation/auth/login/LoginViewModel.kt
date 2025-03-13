package com.example.baseandroidproject.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import com.example.baseandroidproject.domain.singletons.DataStoreKeys
import com.example.baseandroidproject.domain.usecases.auth.LoginUseCase
import com.example.baseandroidproject.domain.usecases.datastore.AddPreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase,
    private val dataStore: AddPreferenceUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow<Resource<AuthResponse>?>(null)
    val loginState = _loginState

    private val _loginEvents = Channel<LoginEvent>()
    val loginEvents = _loginEvents.receiveAsFlow()

    fun loginUser(email : String, password : String,rememberMe : Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.invoke(email = email, password = password).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        val token = response.data.token
                        dataStore(DataStoreKeys.UserEmail,email)
                        dataStore(DataStoreKeys.UserToken, token)
                        dataStore(DataStoreKeys.RememberMe,rememberMe)
                        _loginEvents.send(LoginEvent.NavigateToHome)
                    }
                    is Resource.Error -> {
                        _loginEvents.send(LoginEvent.ShowError(response.message))
                    }
                    is Resource.Loading -> {
                    }
                }
                _loginState.value = response
            }
        }
    }


}