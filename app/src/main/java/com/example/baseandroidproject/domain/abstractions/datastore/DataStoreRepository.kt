package com.example.baseandroidproject.domain.abstractions.datastore

import com.example.baseandroidproject.domain.abstractions.PreferenceKey
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    fun <T> getPreference(key : PreferenceKey<T>) : Flow<T>

    suspend fun <T> addPreference(key : PreferenceKey<T>,value : T)

    suspend fun clearAllPreferences()

}