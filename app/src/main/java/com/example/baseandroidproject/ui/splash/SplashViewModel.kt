package com.example.baseandroidproject.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.sessions.DataStore
import kotlinx.coroutines.launch

class SplashViewModel(private val dataStore: DataStore) : ViewModel() {

    fun checkForToken(tokenCheck : (Boolean) -> Unit){
        viewModelScope.launch {
            dataStore.getToken().collect{ token ->
                tokenCheck(token != null)
            }
        }
    }

}