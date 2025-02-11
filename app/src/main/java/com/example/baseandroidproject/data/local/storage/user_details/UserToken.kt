package com.example.baseandroidproject.data.local.storage.user_details

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_tokens")
data class UserToken(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "token") val token: String?
)