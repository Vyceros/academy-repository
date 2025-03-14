package com.example.baseandroidproject.data.user

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baseandroidproject.data.user.dao.UserDao
import com.example.baseandroidproject.data.user.models.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}