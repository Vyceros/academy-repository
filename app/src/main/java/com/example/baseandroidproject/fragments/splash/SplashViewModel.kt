package com.example.baseandroidproject.fragments.splash

import com.example.baseandroidproject.client.response_handler.ApiResponseHandler
import com.example.baseandroidproject.sessions.DataStore

class SplashViewModel(dataStore: DataStore) : ApiResponseHandler() {

    val tokenCall = dataStore.getToken()

}