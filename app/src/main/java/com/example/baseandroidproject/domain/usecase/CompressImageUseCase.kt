package com.example.baseandroidproject.domain.usecase

import com.example.baseandroidproject.domain.repository.CompressImageRepository
import javax.inject.Inject

class CompressImageUseCase @Inject constructor(
    private val repo : CompressImageRepository
) {
    suspend operator fun invoke(uri : String) : ByteArray?{
        return repo.compressImage(uri)
    }
}