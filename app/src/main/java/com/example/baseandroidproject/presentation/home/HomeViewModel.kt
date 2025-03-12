package com.example.baseandroidproject.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.baseandroidproject.domain.abstractions.InternetObserver
import com.example.baseandroidproject.domain.usecases.user.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(useCase: GetUsersUseCase,connectivityManager: InternetObserver) : ViewModel() {
    val items = useCase.invoke().cachedIn(viewModelScope)

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