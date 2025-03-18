package com.example.baseandroidproject.di

import com.example.baseandroidproject.data.auth.repositories.AuthLoginRepositoryImpl
import com.example.baseandroidproject.data.auth.repositories.AuthRegisterRepositoryImpl
import com.example.baseandroidproject.data.common.connection_observer.InternetObserverImpl
import com.example.baseandroidproject.data.common.session.SessionRepositoryImpl
import com.example.baseandroidproject.data.datastore.DataStoreHelperImpl
import com.example.baseandroidproject.data.user.repositories.UserRepositoryImpl
import com.example.baseandroidproject.domain.abstractions.auth.AuthLoginRepository
import com.example.baseandroidproject.domain.abstractions.auth.AuthRegisterRepository
import com.example.baseandroidproject.domain.abstractions.datastore.DataStoreHelper
import com.example.baseandroidproject.domain.abstractions.internet_observer.InternetObserver
import com.example.baseandroidproject.domain.abstractions.session.SessionRepository
import com.example.baseandroidproject.domain.abstractions.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class  AppModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl) : UserRepository

    @Binds
    abstract fun bindsAuthRegisterRepository(registerImpl: AuthRegisterRepositoryImpl) : AuthRegisterRepository

    @Binds
    abstract fun bindsAuthLoginRepository(loginRepositoryImpl: AuthLoginRepositoryImpl) : AuthLoginRepository

    @Binds
    abstract fun bindDataStoreRepository(dataStoreRepositoryImpl: DataStoreHelperImpl) : DataStoreHelper

    @Binds
    abstract fun bindsInternetRepo(internetRepoImpl : InternetObserverImpl) : InternetObserver

    @Binds
    abstract fun bindsSessionRepository(sessionRepository: SessionRepositoryImpl) : SessionRepository
}