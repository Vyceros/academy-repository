package com.example.baseandroidproject.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.local.entities.UserEntity
import com.example.baseandroidproject.data.repositories.data_store.DataStoreRepository
import com.example.baseandroidproject.data.repositories.user_repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel() {

    private val _userDetails = MutableStateFlow<UserEntity?>(null)
    val userDetails = _userDetails

    init {
        loadUserDetails()
    }


    fun logOut() {
        viewModelScope.launch {
            dataStoreRepository.clearStore()
        }
    }

    fun updateUserDetails(email: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            val user = _userDetails.value
            val updatedUser = user?.copy(email = email, firstName = firstName, lastName = lastName)
            updatedUser?.let { userRepositoryImpl.insertUser(it) }
        }
    }

    fun loadUserDetails() {
        viewModelScope.launch {
            dataStoreRepository.getUserEmail().collect { email ->
                email?.let { userEmail ->
                    val user = userRepositoryImpl.getUserByEmail(userEmail)
                    _userDetails.value = user
                }
            }
        }
    }
}