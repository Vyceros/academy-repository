package com.example.baseandroidproject.data.di

import android.content.Context
import androidx.room.Room
import com.example.baseandroidproject.data.user.AppDatabase
import com.example.baseandroidproject.data.user.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    fun provideRoomDatabase(@ApplicationContext context : Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }


}