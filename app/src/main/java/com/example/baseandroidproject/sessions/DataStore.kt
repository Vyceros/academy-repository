package com.example.baseandroidproject.sessions

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(private val context: Context) {

    suspend fun addToken(token: String) {
        context.dataStore.edit {
            it[TOKEN] = token
        }
    }

    suspend fun addEmail(email: String) {
        context.dataStore.edit {
            it[EMAIL] = email
        }
    }

    fun getEmail(): Flow<String?> {
        return context.dataStore.data.map {
            it[EMAIL]
        }
    }

    suspend fun clearStore(){
        context.dataStore.edit {
            it.remove(TOKEN)
            it.remove(EMAIL)
        }
    }

    fun getToken(): Flow<String?> {
        return context.dataStore.data.map {
            it[TOKEN]
        }
    }


    companion object {
        private val Context.dataStore by preferencesDataStore("user_token")
        val EMAIL = stringPreferencesKey("email")
        private val TOKEN = stringPreferencesKey("token")

    }
}