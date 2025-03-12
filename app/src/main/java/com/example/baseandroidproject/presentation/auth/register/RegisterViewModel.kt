package com.example.baseandroidproject.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthRequest
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import com.example.baseandroidproject.domain.usecases.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val useCase: RegisterUseCase) : ViewModel() {
    private val _registerCall = MutableStateFlow<Resource<AuthResponse>?>(null)
    val registerCall = _registerCall.asStateFlow()


    fun registerUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.invoke(AuthRequest(email = email, password))
                .collect { response ->
                    _registerCall.value = response
                }
        }
    }

}