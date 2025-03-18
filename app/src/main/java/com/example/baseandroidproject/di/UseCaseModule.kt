package com.example.baseandroidproject.di

import com.example.baseandroidproject.domain.usecases.validations.ValidateEmailUseCase
import com.example.baseandroidproject.domain.usecases.validations.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideEmail() : ValidateEmailUseCase {
        return ValidateEmailUseCase()
    }

    @Provides
    fun providePassword() : ValidatePasswordUseCase {
        return ValidatePasswordUseCase()
    }
}