package com.example.baseandroidproject.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.baseandroidproject.data.remote.connection_observer.InternetObserverImpl
import com.example.baseandroidproject.data.repositories.user_repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(userRepository: UserRepositoryImpl,connectivityManager: InternetObserverImpl) : ViewModel() {
    val items = userRepository.getUsers().cachedIn(viewModelScope)

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected

    init {
        viewModelScope.launch {
            connectivityManager.isConnected.collectLatest { isConnected ->
                _isConnected.value = isConnected
            }
        }
    }

}