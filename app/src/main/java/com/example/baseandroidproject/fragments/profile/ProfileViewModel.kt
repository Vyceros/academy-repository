package com.example.baseandroidproject.fragments.profile

import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.client.response_handler.ApiResponseHandler
import com.example.baseandroidproject.sessions.DataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProfileViewModel(private val dataStore : DataStore) : ApiResponseHandler() {

    fun retrieveUserEmail() : Flow<String?> = dataStore.getEmail()

    fun logout(){
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.clearStore()
        }
    }
}