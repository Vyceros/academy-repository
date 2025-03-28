package com.example.baseandroidproject.presentation.screen.gallery

import android.net.Uri

data class GalleryUiState (
    val uploading : Boolean = false,
    val compressedBytes : ByteArray? = null,
    val tempUri : Uri? = null
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GalleryUiState

        if (compressedBytes != null) {
            if (other.compressedBytes == null) return false
            if (!compressedBytes.contentEquals(other.compressedBytes)) return false
        } else if (other.compressedBytes != null) return false

        return true
    }

    override fun hashCode(): Int {
        return compressedBytes?.contentHashCode() ?: 0
    }

}