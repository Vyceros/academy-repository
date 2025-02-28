package com.example.baseandroidproject.data.di.app_module

import com.example.baseandroidproject.data.repository.LocationRepositoryImpl
import com.example.baseandroidproject.domain.abstractions.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideLocationRepository(repositoryImpl: LocationRepositoryImpl): LocationRepository
}