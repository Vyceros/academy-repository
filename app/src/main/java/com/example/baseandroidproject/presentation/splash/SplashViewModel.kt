package com.example.baseandroidproject.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.abstractions.datastore.DataStoreRepository
import com.example.baseandroidproject.domain.singletons.DataStoreKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val dataStore: DataStoreRepository) : ViewModel() {

    private val _navigationFlow = MutableStateFlow<Navigation>(Navigation.Idle)
    val navigationFlow = _navigationFlow.asStateFlow()

    init {
        checkToken()
    }

    private fun checkToken() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.getPreference(DataStoreKeys.RememberMe).collect{ state ->
                if(state){
                    _navigationFlow.value = Navigation.HomeScreen
                }else{
                    _navigationFlow.value = Navigation.LoginScreen
                }
            }
        }
    }
}