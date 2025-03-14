package com.example.baseandroidproject.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthRequest
import com.example.baseandroidproject.domain.singletons.DataStoreKeys
import com.example.baseandroidproject.domain.usecases.auth.LoginUseCase
import com.example.baseandroidproject.domain.usecases.datastore.AddPreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase,
    private val dataStore: AddPreferenceUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState = _loginState.asStateFlow()

    private val _loginEvents = Channel<LoginEvent>()
    val loginEvents = _loginEvents.receiveAsFlow()

    fun loginUser(email : String, password : String,rememberMe : Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase(AuthRequest(email = email, password = password)).
            onStart {
                _loginState.value = LoginState.Loading
            }.catch {
                _loginState.value = LoginState.Error()
            }.collect{ result ->
                _loginState.value = when(result){
                    is Resource.Error -> {
                        _loginEvents.send(LoginEvent.ShowError(result.message))
                        LoginState.Error(result.message)
                    }
                    is Resource.Loading -> {
                        LoginState.Loading
                    }
                    is Resource.Success -> {
                        dataStore(DataStoreKeys.UserEmail,email)
                        dataStore(DataStoreKeys.RememberMe,rememberMe)
                        _loginEvents.send(LoginEvent.NavigateToHome)
                        LoginState.Success
                    }
                }

            }
        }

    }
}