package com.example.baseandroidproject.data.di

import android.content.Context
import androidx.work.WorkManager
import com.example.baseandroidproject.data.util.SafeCall
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun providesFirebaseStorage() : FirebaseStorage{
        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun providesSafeCall() : SafeCall{
        return SafeCall()
    }

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context : Context) : WorkManager{
        return WorkManager.getInstance(context)
    }
}