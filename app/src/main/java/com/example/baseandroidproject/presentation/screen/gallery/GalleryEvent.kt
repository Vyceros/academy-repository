package com.example.baseandroidproject.presentation.screen.gallery

import android.net.Uri

sealed interface GalleryEvent {
    data object CompressImage : GalleryEvent
    data class GetUri(val uri: Uri): GalleryEvent
}