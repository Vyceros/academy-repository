package com.example.baseandroidproject.domain.models

data class Photo(
    val uri : String,
    val compressedUri : String? = null,
    val compressionPercentage : Long = 80L
)
