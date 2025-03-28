package com.example.baseandroidproject.domain.usecase

import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.repository.UploadImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
    private val repository: UploadImageRepository
) {
    suspend operator fun invoke(byteArray: ByteArray) : Flow<Resource<Unit>> {
        return repository.uploadImage(byteArray)
    }
}