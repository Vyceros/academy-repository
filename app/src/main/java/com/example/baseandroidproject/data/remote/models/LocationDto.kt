package com.example.baseandroidproject.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val lat: Double,
    val lan: Double,
    val title : String,
    val address : String
)
