package com.example.baseandroidproject.fragments.profile

import UserDetail
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.helpers.Validators
import com.example.baseandroidproject.sessions.ProtoDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ProfileViewModel(private val dataStore: ProtoDataStore) : ViewModel() {
    private val _userFlow = MutableStateFlow<UserDetail?>(null)
    val userFlow = _userFlow.asStateFlow()


    fun loadUserDetails() {
        viewModelScope.launch {
            dataStore.getUserDetail().firstOrNull()?.let {
                _userFlow.value = it
            }
        }
    }


    fun updateUserDetails(firstName: String, lastName: String, email: String): Flow<Boolean> {
        return flow {
            try {
                if (validateEmail(email) && validateName(firstName) && validateName(lastName)) {
                    dataStore.updateUserDetail(firstName, lastName, email)
                    emit(true)
                }else{
                    emit(false)
                }
            } catch (er: Throwable) {
                emit(false)
            }
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.clearUserDetail()
        }
    }

    private val validator = Validators()

    private fun validateEmail(email: String): Boolean = validator.validateEmail(email)

    private fun validateName(name: String): Boolean = validator.validateName(name)

}
