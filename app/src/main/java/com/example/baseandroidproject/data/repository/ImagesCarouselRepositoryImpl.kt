package com.example.baseandroidproject.data.repository

import com.example.baseandroidproject.data.remote.models.ImageCardDto
import com.example.baseandroidproject.data.remote.services.ImageService
import com.example.baseandroidproject.data.resource.Resource
import com.example.baseandroidproject.data.utils.ApiSafeCallHandler
import com.example.baseandroidproject.domain.abstractions.ImagesCarouselRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImagesCarouselRepositoryImpl @Inject constructor(
    private val imageService: ImageService,
    private val apiSafeCall : ApiSafeCallHandler
) : ImagesCarouselRepository {

    override suspend fun fetchImages(): Flow<Resource<List<ImageCardDto>>> {
        return apiSafeCall.apiCall {
            imageService.fetchImages()
        }
    }
}