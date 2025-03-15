package com.example.baseandroidproject.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.singletons.DataStoreKeys
import com.example.baseandroidproject.domain.usecases.datastore.ClearPreferencesUseCase
import com.example.baseandroidproject.domain.usecases.datastore.GetPreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logOut: ClearPreferencesUseCase,
    private val getUseCase : GetPreferenceUseCase
) : ViewModel() {

    private val _userDetails = MutableStateFlow("")
    val userDetails = _userDetails.asStateFlow()

    private val _logoutEvent = Channel<ProfileEvent>()
    val logoutEvent = _logoutEvent.receiveAsFlow()

    fun logout(){
        viewModelScope.launch(Dispatchers.IO) {
            logOut.invoke()
            _logoutEvent.send(ProfileEvent.Logout)
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