package com.example.baseandroidproject.data.common.session

import com.example.baseandroidproject.domain.abstractions.datastore.DataStoreHelper
import com.example.baseandroidproject.domain.abstractions.session.SessionRepository
import com.example.baseandroidproject.domain.singletons.DataStoreKeys
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val dataStore: DataStoreHelper
) : SessionRepository {
    override suspend fun saveSession(email: String, token: String, rememberMe: Boolean) {
        dataStore.addPreference(DataStoreKeys.UserEmail,email)
        dataStore.addPreference(DataStoreKeys.UserToken,token)
        dataStore.addPreference(DataStoreKeys.RememberMe,rememberMe)
    }

    override suspend fun checkSession(): Flow<Boolean> {
        return dataStore.getPreference(DataStoreKeys.RememberMe)
    }
}