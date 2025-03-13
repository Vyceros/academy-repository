package com.example.baseandroidproject.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.singletons.DataStoreKeys
import com.example.baseandroidproject.domain.usecases.datastore.GetPreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val dataStore: GetPreferenceUseCase) : ViewModel() {

    private val _navigationEvent = Channel<NavigationEvent>()
    val navigationFlow = _navigationEvent.receiveAsFlow()

    init {
        checkToken()
    }

    private fun checkToken() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore(DataStoreKeys.RememberMe).collect{ state ->
                if(state){
                    _navigationEvent.send(NavigationEvent.HomeScreen)
                }else{
                    _navigationEvent.send(NavigationEvent.LoginScreen)
                }
            }
        }
    }
}