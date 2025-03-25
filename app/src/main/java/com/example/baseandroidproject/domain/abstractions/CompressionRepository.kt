package com.example.baseandroidproject.domain.abstractions

import com.example.baseandroidproject.domain.models.Photo


interface CompressionRepository {
    suspend fun compressPhoto(photo : Photo) : Photo
}