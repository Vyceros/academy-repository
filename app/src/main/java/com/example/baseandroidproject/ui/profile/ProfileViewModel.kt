package com.example.baseandroidproject.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.local.entities.UserEntity
import com.example.baseandroidproject.data.repositories.user_repository.UserRepository
import com.example.baseandroidproject.data.sessions.DataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val dataStore: DataStore,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userDetails = MutableStateFlow<UserEntity?>(null)
    val userDetails = _userDetails

    fun logOut() {
        viewModelScope.launch {
            dataStore.clearStore()
        }
    }

    fun updateUserDetails(email: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            val user = _userDetails.value
            val updatedUser = user?.copy(email = email, firstName = firstName, lastName = lastName)
            updatedUser?.let { userRepository.insertUser(it) }
            _userDetails.value = updatedUser
        }
    }

    fun loadUserDetails() {
        viewModelScope.launch {
            dataStore.getUserId().collect { userId ->
                userId?.let { id ->
                    val user = userRepository.getUserById(id)
                    _userDetails.value = user
                }
            }
        }
    }
}
