package com.example.baseandroidproject.storage.user_details

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DetailsDao {

    @Query("SELECT * FROM user_details")
    suspend fun getUserDetail() : UserDetailsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetails(userDetailsEntity: UserDetailsEntity)

    @Query("DELETE FROM user_details")
    suspend fun clearDetails()
}