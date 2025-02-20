package com.example.baseandroidproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baseandroidproject.data.local.dao.RemoteKeysDao
import com.example.baseandroidproject.data.local.dao.UserDao
import com.example.baseandroidproject.data.local.entities.RemoteKeys
import com.example.baseandroidproject.data.local.entities.UserEntity

@Database(entities = [UserEntity::class, RemoteKeys::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun remoteKeysDao() : RemoteKeysDao
}