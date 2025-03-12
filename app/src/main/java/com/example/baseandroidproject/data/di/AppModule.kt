package com.example.baseandroidproject.data.di

import com.example.baseandroidproject.data.remote.connection_observer.InternetObserverImpl
import com.example.baseandroidproject.data.repositories.auth_repository.AuthLoginRepositoryImpl
import com.example.baseandroidproject.data.repositories.auth_repository.AuthRegisterRepositoryImpl
import com.example.baseandroidproject.data.repositories.data_store.DataStoreRepositoryImpl
import com.example.baseandroidproject.data.repositories.user_repository.UserRepositoryImpl
import com.example.baseandroidproject.domain.abstractions.InternetObserver
import com.example.baseandroidproject.domain.abstractions.auth.AuthLoginRepository
import com.example.baseandroidproject.domain.abstractions.auth.AuthRegisterRepository
import com.example.baseandroidproject.domain.abstractions.datastore.DataStoreRepository
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
    abstract fun bindDataStoreRepository(dataStoreRepositoryImpl: DataStoreRepositoryImpl) : DataStoreRepository

    @Binds
    abstract fun bindsInternetRepo(internetRepoImpl : InternetObserverImpl) : InternetObserver
}