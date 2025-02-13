package com.example.baseandroidproject.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.local.entities.UserEntity
import com.example.baseandroidproject.data.remote.models.auth.AuthRequest
import com.example.baseandroidproject.data.remote.models.auth.AuthResponse
import com.example.baseandroidproject.data.repositories.auth_repository.AuthRepository
import com.example.baseandroidproject.data.repositories.data_store.DataStoreRepository
import com.example.baseandroidproject.data.repositories.user_repository.UserRepository
import com.example.baseandroidproject.data.resource.Resource
import com.example.baseandroidproject.domain.utils.validateEmails
import com.example.baseandroidproject.domain.utils.validatePasswords
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreRepository: DataStoreRepository, private val userRepo: UserRepository
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

                if (response is Resource.Success && response.data != null) {
                    val token = response.data.token
                    if (rememberMe) {
                        dataStoreRepository.addToken(token)
                        dataStoreRepository.addUserEmail(email)
                    }

                    saveUserDetails(firstName, lastName, email, rememberMe)
                }
                _loginState.value = response
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
            val user = UserEntity(
                firstName = firstName,
                lastName = lastName,
                email = email,
                avatar = null
            )
            userRepo.insertUser(user)
        }
    }


}