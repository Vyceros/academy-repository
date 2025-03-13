package com.example.baseandroidproject.domain.usecases.datastore

import com.example.baseandroidproject.domain.abstractions.PreferenceKey
import com.example.baseandroidproject.domain.abstractions.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPreferenceUseCase @Inject constructor(
    private val repo : DataStoreRepository
) {
    operator fun<T> invoke(key : PreferenceKey<T>): Flow<T> {
        return repo.getPreference(key)
    }
}