package com.example.baseandroidproject.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.abstractions.auth.AuthRegisterRepository
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthRequest
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repo: AuthRegisterRepository) : ViewModel() {
    private val _registerCall = MutableStateFlow<Resource<AuthResponse>?>(null)
    val registerCall = _registerCall.asStateFlow()


    fun registerUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.registerUser(AuthRequest(email = email, password))
                .collect { response ->
                    _registerCall.value = response
                }
        }
    }

}