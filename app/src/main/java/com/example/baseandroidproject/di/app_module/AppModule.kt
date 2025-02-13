package com.example.baseandroidproject.di.app_module

import com.example.baseandroidproject.data.helpers.ApiResponseHandler
import com.example.baseandroidproject.data.local.AppDatabase
import com.example.baseandroidproject.data.remote.api.AuthorizationService
import com.example.baseandroidproject.data.remote.api.UserService
import com.example.baseandroidproject.data.repositories.abstractions.IAuthRepository
import com.example.baseandroidproject.data.repositories.abstractions.IUserRepository
import com.example.baseandroidproject.data.repositories.auth_repository.AuthRepository
import com.example.baseandroidproject.data.repositories.user_repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesUserRepository(database : AppDatabase,userService : UserService) : IUserRepository {
        return UserRepository(userService,database)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authService : AuthorizationService, apiResponseHandler: ApiResponseHandler) : IAuthRepository {
        return AuthRepository(authService,apiResponseHandler)
    }
}