package com.example.baseandroidproject.data.remote.services

import com.example.baseandroidproject.data.remote.models.LocationDto
import retrofit2.Response
import retrofit2.http.GET

interface LocationService {
    @GET("c4c64996-4ed9-4cbc-8986-43c4990d495a")
    suspend fun getLocation() : Response<List<LocationDto>>
}