package com.example.baseandroidproject.storage.user_list

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun pagingSource(): PagingSource<Int, UserEntity>

    @Upsert
    suspend fun insertAll(users: List<UserEntity>)

    @Query("DELETE FROM users")
    suspend fun clearAll()
}
