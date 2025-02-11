package com.example.baseandroidproject.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.baseandroidproject.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): PagingSource<Int,UserEntity>

    @Upsert
    suspend fun insertUsers(users: List<UserEntity>)

    @Upsert
    suspend fun insertOneUser(user: UserEntity)

    @Query("DELETE FROM users")
    suspend fun clearAllUsers()

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Int): UserEntity?

}