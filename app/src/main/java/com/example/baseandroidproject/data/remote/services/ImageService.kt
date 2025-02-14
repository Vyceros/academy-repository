package com.example.baseandroidproject.data.remote.services

import com.example.baseandroidproject.data.remote.models.ImageCardDto
import retrofit2.Response
import retrofit2.http.GET

interface ImageService {

    @GET("6dffd14a-836f-4566-b024-bd41ace3a874")
    suspend fun fetchImages() : Response<List<ImageCardDto>>
}
