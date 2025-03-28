package com.example.baseandroidproject.domain.repository

interface CompressImageRepository {
    suspend fun compressImage(uri: String) : ByteArray?
}