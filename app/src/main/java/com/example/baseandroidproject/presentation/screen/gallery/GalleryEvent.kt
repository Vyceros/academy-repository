package com.example.baseandroidproject.presentation.screen.gallery

import android.net.Uri

sealed interface GalleryEvent {
    data object ProcessImage : GalleryEvent
    data class UriCreated(val uri: Uri): GalleryEvent
}