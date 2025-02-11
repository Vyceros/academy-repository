package com.example.baseandroidproject.data.sessions

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(private val context : Context) {

    suspend fun addToken(token: String) {
        context.dataStore.edit {
            it[TOKEN] = token
        }
    }

    suspend fun clearStore(){
        context.dataStore.edit {
            it.remove(TOKEN)
        }
    }

    fun getToken(): Flow<String?> {
        return context.dataStore.data.map {
            it[TOKEN]
        }
    }

    companion object{
        private val Context.dataStore by preferencesDataStore("user_token")
        private val TOKEN = stringPreferencesKey("token")
    }
}