package com.example.baseandroidproject.fragments.splash

import UserDetail
import com.example.baseandroidproject.client.response_handler.ApiResponseHandler
import com.example.baseandroidproject.sessions.ProtoDataStore
import kotlinx.coroutines.flow.Flow

class SplashViewModel(private val dataStore: ProtoDataStore) : ApiResponseHandler() {

    fun retrieveToken() : Flow<UserDetail> {
        return dataStore.getUserDetail()
    }

}