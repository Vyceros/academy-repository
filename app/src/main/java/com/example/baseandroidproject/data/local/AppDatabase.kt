package com.example.baseandroidproject.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.baseandroidproject.data.local.dao.UserDao
import com.example.baseandroidproject.data.local.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private lateinit var instance: AppDatabase

        fun getInstance(context: Context): AppDatabase {
            synchronized(AppDatabase::class.java) {
                if (!::instance.isInitialized) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                }
            }
            return instance
        }
    }
}