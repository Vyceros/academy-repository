package com.example.baseandroidproject.data.di.datastore_module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore("user_info")

    @Provides
    fun provideDataStore(@ApplicationContext context : Context) : DataStore<Preferences> {
        return context.userDataStore
    }
}