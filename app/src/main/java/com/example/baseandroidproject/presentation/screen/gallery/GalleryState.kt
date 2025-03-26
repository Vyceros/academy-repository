package com.example.baseandroidproject.presentation.screen.gallery

import android.graphics.Bitmap
import android.net.Uri

data class GalleryState(
    val compressedImage: Bitmap? = null,
    val tempUri: Uri? = null
)
