package com.example.baseandroidproject.data.di.app_module

import com.example.baseandroidproject.data.helpers.ApiResponseHandler
import com.example.baseandroidproject.data.local.AppDatabase
import com.example.baseandroidproject.data.remote.api.AuthorizationService
import com.example.baseandroidproject.data.remote.api.UserService
import com.example.baseandroidproject.data.remote.connection_observer.InternetObserverImpl
import com.example.baseandroidproject.data.repositories.auth_repository.AuthRepositoryImpl
import com.example.baseandroidproject.data.repositories.user_repository.UserRepositoryImpl
import com.example.baseandroidproject.domain.abstractions.AuthRepository
import com.example.baseandroidproject.domain.abstractions.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesUserRepository(database : AppDatabase,userService : UserService,conManager : InternetObserverImpl) : UserRepository {
        return UserRepositoryImpl(userService,database,conManager)
    }

    @Provides
    fun provideAuthRepository(authService : AuthorizationService, apiResponseHandler: ApiResponseHandler) : AuthRepository {
        return AuthRepositoryImpl(authService,apiResponseHandler)
    }
}