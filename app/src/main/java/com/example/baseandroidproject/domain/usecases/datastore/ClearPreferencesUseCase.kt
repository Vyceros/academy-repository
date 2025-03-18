package com.example.baseandroidproject.domain.usecases.datastore

import com.example.baseandroidproject.domain.abstractions.datastore.DataStoreHelper
import javax.inject.Inject

class ClearPreferencesUseCase @Inject constructor(
    private val repo : DataStoreHelper
){
    suspend operator fun invoke(){
        return repo.clearAllPreferences()
    }
}
