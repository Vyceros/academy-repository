package com.example.baseandroidproject.domain.usecases.datastore

import com.example.baseandroidproject.domain.abstractions.PreferenceKey
import com.example.baseandroidproject.domain.abstractions.datastore.DataStoreRepository
import javax.inject.Inject

class AddPreferenceUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun<T> invoke(key : PreferenceKey<T>, value : T){
        return dataStoreRepository.addPreference(key,value)
    }
}