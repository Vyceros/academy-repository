package com.example.baseandroidproject.storage.user_list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)
