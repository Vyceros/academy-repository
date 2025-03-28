package com.example.baseandroidproject.domain.repository

import com.example.baseandroidproject.domain.common.Resource
import kotlinx.coroutines.flow.Flow

interface UploadImageRepository {
    suspend fun uploadImage(byteArray : ByteArray) : Flow<Resource<Unit>>
}