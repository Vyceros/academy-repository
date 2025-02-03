package com.example.baseandroidproject.persistence.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUsers(users : List<UserEntity>)

    @Query("SELECT * FROM users")
    fun getAllUsers() : Flow<List<UserEntity>>
}