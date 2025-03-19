package com.example.baseandroidproject.di

import com.example.baseandroidproject.domain.usecases.CategoriesUseCase
import com.example.baseandroidproject.domain.usecases.CategoriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindsUseCase(useCase: CategoriesUseCaseImpl): CategoriesUseCase
}