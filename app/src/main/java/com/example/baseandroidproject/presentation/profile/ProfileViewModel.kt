package com.example.baseandroidproject.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.singletons.DataStoreKeys
import com.example.baseandroidproject.domain.usecases.datastore.ClearPreferencesUseCase
import com.example.baseandroidproject.domain.usecases.datastore.GetPreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStore: ClearPreferencesUseCase,
    private val getUseCase : GetPreferenceUseCase
) : ViewModel() {

    private val _userDetails = MutableStateFlow("")
    val userDetails = _userDetails.asStateFlow()

    fun logOut() {
        viewModelScope.launch {
            dataStore.invoke()
        }
    }


    fun loadUserDetails() {
        viewModelScope.launch {
            getUseCase(DataStoreKeys.UserEmail).collect { email ->
                email.let {
                    _userDetails.value = it
                }
            }
        }
    }
}