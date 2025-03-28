package com.example.baseandroidproject.presentation.screen.gallery

import android.net.Uri

sealed interface GalleryEvents {
    data object CompressImage : GalleryEvents
    data class CreateTempUri(val uri: Uri) : GalleryEvents
    data object UploadImage : GalleryEvents
}