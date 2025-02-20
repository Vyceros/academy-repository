package com.example.baseandroidproject.data.di.app_module

import com.example.baseandroidproject.data.repositories.auth_repository.AuthRepositoryImpl
import com.example.baseandroidproject.data.repositories.user_repository.UserRepositoryImpl
import com.example.baseandroidproject.domain.abstractions.AuthRepository
import com.example.baseandroidproject.domain.abstractions.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class  AppModule {

    @Binds
    abstract fun providesUserRepository(userRepositoryImpl: UserRepositoryImpl) : UserRepository

    @Binds
    abstract fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl) : AuthRepository
}