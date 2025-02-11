package com.example.baseandroidproject.data.local.storage.user_list

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, UserEntity>

    @Upsert
    suspend fun insertAll(users: List<UserEntity>)

    @Upsert
    suspend fun insertDetails(user: UserEntity)

    @Query("DELETE FROM users")
    suspend fun clearAll()
}
