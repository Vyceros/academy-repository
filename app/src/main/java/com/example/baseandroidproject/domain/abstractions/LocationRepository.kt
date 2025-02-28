package com.example.baseandroidproject.domain.abstractions

import com.example.baseandroidproject.data.remote.models.LocationDto
import com.example.baseandroidproject.data.remote.models.Resource
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun getLocation() : Flow<Resource<List<LocationDto>>>
}