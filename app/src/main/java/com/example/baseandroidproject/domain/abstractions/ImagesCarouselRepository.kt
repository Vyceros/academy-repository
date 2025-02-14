package com.example.baseandroidproject.domain.abstractions

import com.example.baseandroidproject.data.remote.models.ImageCardDto
import com.example.baseandroidproject.data.resource.Resource
import kotlinx.coroutines.flow.Flow

interface ImagesCarouselRepository {
    suspend fun fetchImages() : Flow<Resource<List<ImageCardDto>>>
}