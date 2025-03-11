package com.example.baseandroidproject.data.repositories.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.baseandroidproject.data.utils.mappers.mapPreferenceKey
import com.example.baseandroidproject.domain.abstractions.PreferenceKey
import com.example.baseandroidproject.domain.abstractions.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(private val dataStore : DataStore<Preferences>) : DataStoreRepository  {
    override fun <T> getPreference(key: PreferenceKey<T>): Flow<T> {
        return dataStore.data.map { pref ->
            pref[mapPreferenceKey(key)] ?: key.defaultValue
        }
    }

    override suspend fun <T> addPreference(key: PreferenceKey<T>,value : T) {
        dataStore.edit { pref ->
            pref[mapPreferenceKey(key)] = value
        }
    }

    override suspend fun clearAllPreferences() {
        dataStore.edit { it.clear() }
    }
}