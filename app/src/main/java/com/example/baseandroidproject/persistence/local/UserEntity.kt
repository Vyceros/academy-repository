package com.example.baseandroidproject.persistence.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val avatar: String?,
    val firstName: String,
    val lastName: String,
    val about: String,
    val activationStatus: String
)