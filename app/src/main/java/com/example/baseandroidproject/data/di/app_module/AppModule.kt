package com.example.baseandroidproject.data.di.app_module

import com.example.baseandroidproject.data.remote.services.ImageService
import com.example.baseandroidproject.data.repository.ImagesCarouselRepositoryImpl
import com.example.baseandroidproject.data.utils.ApiSafeCallHandler
import com.example.baseandroidproject.domain.abstractions.ImagesCarouselRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideImageRepository(imageService : ImageService, apiSafeCall : ApiSafeCallHandler) : ImagesCarouselRepository{
        return ImagesCarouselRepositoryImpl(imageService,apiSafeCall)
    }
}