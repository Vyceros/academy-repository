package com.example.baseandroidproject.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.local.repos.UserDetailsRepository
import com.example.baseandroidproject.data.local.storage.user_details.UserToken
import com.example.baseandroidproject.helpers.Validators
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val userDetailsRepository: UserDetailsRepository) : ViewModel() {
    private val _userDetails = MutableStateFlow<UserToken?>(null)
    val userDetails = _userDetails.asStateFlow()

    fun loadUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userDetailsRepository.getUserDetails()
            _userDetails.value = user
        }
    }

    fun saveProfileDetails(firstName: String, lastName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val userDetails = userDetailsRepository.getUserDetails()

            if (validateName(firstName) && validateName(lastName)) {
                userDetails?.let {
                    val updatedUser = it.copy(token = userDetails.token)
                    userDetailsRepository.insertUserDetails(updatedUser)
                    _userDetails.value = updatedUser
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            userDetailsRepository.deleteUserDetails()
        }
    }

    private val validator = Validators()

    private fun validateName(name: String): Boolean = validator.validateName(name)
}
