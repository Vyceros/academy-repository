package com.example.baseandroidproject.sessions

import UserDetail
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore

class ProtoDataStore(private val context: Context) {
    companion object {
        private const val USER_STORAGE_FILE = "user_storage.pb"
        private val Context.dataStore: DataStore<UserDetail> by dataStore(
            fileName = USER_STORAGE_FILE,
            serializer = UserPreferencesSerializer
        )
    }

    fun getUserDetail() = context.dataStore.data

    suspend fun updateUserDetail(
        firstName: String? = null,
        lastName: String? = null,
        email: String? = null,
        token: String? = null
    ) {
        context.dataStore.updateData { preferences ->
            preferences.toBuilder().apply {
                firstName?.let { setFirstName(it) }
                lastName?.let { setLastName(it) }
                email?.let { setEmail(it) }
                token?.let { setToken(it) }
            }.build()
        }
    }

    suspend fun clearUserDetail() {
        context.dataStore.updateData {
            UserDetail.getDefaultInstance()
        }
    }
}
