package com.example.baseandroidproject.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
