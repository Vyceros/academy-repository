package com.example.baseandroidproject.di

import com.example.baseandroidproject.data.repositories.CategoriesRepositoryImpl
import com.example.baseandroidproject.domain.abstractions.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindsCategoryRepo(repo : CategoriesRepositoryImpl) : CategoriesRepository
}