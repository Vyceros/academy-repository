package com.example.baseandroidproject.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.abstractions.datastore.DataStoreRepository
import com.example.baseandroidproject.domain.singletons.DataStoreKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStore: DataStoreRepository,
) : ViewModel() {

    private val _userDetails = MutableStateFlow<String>("")
    val userDetails = _userDetails.asStateFlow()

    fun logOut() {
        viewModelScope.launch {
            dataStore.clearAllPreferences()
        }
    }


    fun loadUserDetails() {
        viewModelScope.launch {
            dataStore.getPreference(DataStoreKeys.UserEmail).collect { email ->
                email.let {
                    _userDetails.value = it
                }
            }
        }
    }
}