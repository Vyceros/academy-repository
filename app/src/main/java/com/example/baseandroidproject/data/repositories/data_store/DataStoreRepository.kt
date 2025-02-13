package com.example.baseandroidproject.data.repositories.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepository @Inject constructor(private val dataStore : DataStore<Preferences>) {

    suspend fun addToken(token: String) {
        dataStore.edit {
            it[TOKEN] = token
        }
    }

    suspend fun clearStore(){
        dataStore.edit {
            it.remove(TOKEN)
            it.remove(USER_ID)
        }
    }

    fun getToken(): Flow<String?> {
        return dataStore.data.map {
            it[TOKEN]
        }
    }

    suspend fun addUserEmail(userEmail: String) {
        dataStore.edit {
            it[USER_ID] = userEmail
        }
    }

    fun getUserEmail(): Flow<String?> {
        return dataStore.data.map {
            it[USER_ID]
        }
    }

    companion object{
        private val USER_ID = stringPreferencesKey("user_email")
        private val TOKEN = stringPreferencesKey("token")
    }
}