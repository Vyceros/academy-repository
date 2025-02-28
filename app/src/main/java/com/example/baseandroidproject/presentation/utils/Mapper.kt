package com.example.baseandroidproject.presentation.utils

import com.example.baseandroidproject.data.remote.models.LocationDto
import com.example.baseandroidproject.presentation.models.Location


fun LocationDto.toPresentation() : Location{
    return Location(
        lat = lat,
        lan = lan,
        title = title,
        address = address
    )
}