package com.example.baseandroidproject.data.local.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.baseandroidproject.data.local.storage.user_details.DetailsDao
import com.example.baseandroidproject.data.local.storage.user_details.UserDetailsEntity
import com.example.baseandroidproject.data.local.storage.user_list.UserDao
import com.example.baseandroidproject.data.local.storage.user_list.UserEntity

@Database(entities = [UserEntity::class, UserDetailsEntity::class], version = 4)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userDetailsDao(): DetailsDao

    companion object {
        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        fun getInstance(context: Context): ApplicationDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration()
                    .build()


                INSTANCE = instance
                instance
            }
        }
    }
}