package com.example.baseandroidproject.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.abstractions.datastore.DataStoreRepository
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthRequest
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import com.example.baseandroidproject.domain.singletons.DataStoreKeys
import com.example.baseandroidproject.domain.usecases.auth.LoginUseCase
import com.example.baseandroidproject.presentation.models.AuthRequestUi
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
    private val dataStore: DataStoreRepository
) : ViewModel() {
    private val _loginState = MutableStateFlow<Resource<AuthResponse>?>(null)
    val loginState = _loginState

    private val _loginEvents = Channel<LoginEvent>()
    val loginEvents = _loginEvents.receiveAsFlow()

    fun loginUser(ui: AuthRequestUi) {
        val request = AuthRequest(
            ui.email,
            ui.password
        )
        viewModelScope.launch(Dispatchers.IO) {
            useCase.invoke(request).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        if (response.data != null) {
                            val token = response.data.token
                            dataStore.addPreference(DataStoreKeys.UserEmail, ui.email)
                            dataStore.addPreference(DataStoreKeys.UserToken, token)
                            dataStore.addPreference(DataStoreKeys.RememberMe, ui.rememberMe)
                            _loginEvents.send(LoginEvent.NavigateToHome)
                        }
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