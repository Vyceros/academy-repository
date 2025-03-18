package com.example.baseandroidproject.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.baseandroidproject.domain.abstractions.PreferenceKey
import com.example.baseandroidproject.domain.abstractions.datastore.DataStoreHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreHelperImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : DataStoreHelper  {
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