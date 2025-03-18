package com.example.baseandroidproject.domain.usecases.datastore

import com.example.baseandroidproject.domain.abstractions.PreferenceKey
import com.example.baseandroidproject.domain.abstractions.datastore.DataStoreHelper
import javax.inject.Inject

class AddPreferenceUseCase @Inject constructor(
    private val dataStoreHelper: DataStoreHelper
) {
    suspend operator fun<T> invoke(key : PreferenceKey<T>, value : T){
        return dataStoreHelper.addPreference(key,value)
    }
}