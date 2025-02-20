package com.example.baseandroidproject.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.repositories.data_store.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) : ViewModel() {

    fun checkForToken(tokenCheck : (Boolean) -> Unit){
        viewModelScope.launch {
            dataStoreRepository.getToken().collect{
                tokenCheck(it != null && dataStoreRepository.getRememberMe().first())
            }
        }
    }

}