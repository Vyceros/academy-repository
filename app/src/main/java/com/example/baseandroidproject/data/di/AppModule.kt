package com.example.baseandroidproject.data.di

import com.example.baseandroidproject.data.imagecompressor.Compressor
import com.example.baseandroidproject.data.repository.UploadImageRepositoryImpl
import com.example.baseandroidproject.domain.repository.CompressImageRepository
import com.example.baseandroidproject.domain.repository.UploadImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindsUploadRepository(repoImpl: UploadImageRepositoryImpl): UploadImageRepository

    @Binds
    abstract fun bindsCompressorRepo(repoImpl : Compressor) : CompressImageRepository
}