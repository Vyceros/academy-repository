package com.example.baseandroidproject.data.repository

import com.example.baseandroidproject.data.remote.models.LocationDto
import com.example.baseandroidproject.data.remote.models.Resource
import com.example.baseandroidproject.data.remote.services.LocationService
import com.example.baseandroidproject.data.utils.ApiSafeCall
import com.example.baseandroidproject.domain.abstractions.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val apiService : LocationService,
    private val safeCall : ApiSafeCall
) : LocationRepository {

    override suspend fun getLocation(): Flow<Resource<List<LocationDto>>> {
        return safeCall.apiCall {
            apiService.getLocation()
        }
    }

}