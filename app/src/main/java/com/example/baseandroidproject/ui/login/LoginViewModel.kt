package com.example.baseandroidproject.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.local.entities.UserEntity
import com.example.baseandroidproject.data.remote.models.auth.AuthRequest
import com.example.baseandroidproject.data.remote.models.auth.AuthResponse
import com.example.baseandroidproject.data.repositories.auth_repository.AuthRepository
import com.example.baseandroidproject.data.repositories.user_repository.UserRepository
import com.example.baseandroidproject.data.resource.Resource
import com.example.baseandroidproject.data.sessions.DataStore
import com.example.baseandroidproject.domain.utils.validateEmails
import com.example.baseandroidproject.domain.utils.validatePasswords
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val dataStore: DataStore, private val userRepo: UserRepository
) : ViewModel() {
    private val _loginState = MutableStateFlow<Resource<AuthResponse>?>(null)
    val loginState = _loginState


    fun loginUser(
        email: String,
        password: String,
        rememberMe: Boolean,
        firstName: String,
        lastName: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.loginUser(AuthRequest(email, password)).collect { response ->
                _loginState.value = response

                if (response is Resource.Success) {
                    if (rememberMe){
                        dataStore.addToken(response.data!!.token)
                        response.data.id?.let { dataStore.addUserId(it) }
                    }
                    saveUserDetails(firstName, lastName, email, rememberMe)
                }
            }
        }
    }

    fun validateEmail(email: String): Boolean = validateEmails(email)

    fun validatePassword(password: String): Boolean = validatePasswords(password)

    private suspend fun saveUserDetails(
        firstName: String,
        lastName: String,
        email: String,
        rememberMe: Boolean
    ) {
        if (rememberMe) {
            userRepo.insertUser(
                UserEntity(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    avatar = null
                )
            )
        }
    }


}