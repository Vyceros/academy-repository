package com.example.baseandroidproject.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.local.entities.UserEntity
import com.example.baseandroidproject.data.remote.models.auth.AuthRequest
import com.example.baseandroidproject.data.remote.models.auth.AuthResponse
import com.example.baseandroidproject.data.repositories.auth_repository.AuthRepositoryImpl
import com.example.baseandroidproject.data.repositories.data_store.DataStoreRepository
import com.example.baseandroidproject.data.repositories.user_repository.UserRepositoryImpl
import com.example.baseandroidproject.data.resource.Resource
import com.example.baseandroidproject.data.utils.validateEmails
import com.example.baseandroidproject.data.utils.validatePasswords
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl,
    private val dataStoreRepository: DataStoreRepository, private val userRepo: UserRepositoryImpl
) : ViewModel() {
    private val _loginState = MutableStateFlow<Resource<AuthResponse>?>(null)
    val loginState = _loginState


    fun loginUser(
        email: String,
        password: String,
        firstName: String = "",
        lastName: String = "",
        rememberMe: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepositoryImpl.loginUser(AuthRequest(email, password)).collect { response ->

                if (response is Resource.Success && response.data != null) {
                    val token = response.data.token

                    dataStoreRepository.addToken(token)
                    dataStoreRepository.addUserEmail(email)
                    dataStoreRepository.saveRememberMe(rememberMe)
                    saveUserDetails(firstName, lastName, email)
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
        email: String
    ) {
        val user = UserEntity(
            firstName = firstName,
            lastName = lastName,
            email = email,
            avatar = null
        )
        userRepo.insertUser(user)
    }


}