package com.example.baseandroidproject.presentation.screen.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.presentation.utils.Compressor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val compressor: Compressor,
) : ViewModel() {

    private val _state = MutableStateFlow(GalleryState())
    val state = _state.asStateFlow()

    fun onEvent(event: GalleryEvent) {
        when (event) {
            is GalleryEvent.CompressImage -> {
                compressImage()
            }

            is GalleryEvent.GetUri -> {
                _state.update { it.copy(tempUri = event.uri) }
            }
        }
    }

    private fun compressImage() {
        viewModelScope.launch(Dispatchers.IO) {
            val uri = state.value.tempUri
            uri?.let {
                val compressedBitmap = compressor.compressImage(uri)
                _state.update { it.copy(compressedImage = compressedBitmap, tempUri = null) }
            }

        }
    }
}