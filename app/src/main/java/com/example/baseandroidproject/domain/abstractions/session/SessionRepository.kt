package com.example.baseandroidproject.domain.abstractions.session

import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun saveSession(email : String, token : String, rememberMe : Boolean)
    suspend fun checkSession() : Flow<Boolean>

}