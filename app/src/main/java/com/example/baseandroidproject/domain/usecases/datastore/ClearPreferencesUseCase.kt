package com.example.baseandroidproject.domain.usecases.datastore

import com.example.baseandroidproject.domain.abstractions.datastore.DataStoreRepository
import javax.inject.Inject

class ClearPreferencesUseCase @Inject constructor(
    private val repo : DataStoreRepository
){
    suspend operator fun invoke(){
        return repo.clearAllPreferences()
    }
}
