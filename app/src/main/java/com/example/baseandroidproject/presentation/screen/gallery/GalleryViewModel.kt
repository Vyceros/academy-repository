package com.example.baseandroidproject.presentation.screen.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.usecase.CompressImageUseCase
import com.example.baseandroidproject.domain.usecase.UploadImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val uploadImage: UploadImageUseCase,
    private val compressImage: CompressImageUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(GalleryUiState())
    val state = _state.asStateFlow()

    private val _events = Channel<GalleryEvents>()
    val events = _events.receiveAsFlow()


    fun onAction(events: GalleryEvents) {
        when (events) {
            is GalleryEvents.CompressImage -> {
                compressImage()
            }

            is GalleryEvents.CreateTempUri -> {

            }

            is GalleryEvents.UploadImage -> {
                uploadImage()
            }
        }
    }

    private fun uploadImage() {
        viewModelScope.launch {
            val array = _state.value.compressedBytes
            uploadImage(array ?: byteArrayOf()).collect { res ->
                _state.update { it.copy(uploading = res is Resource.Loading) }

            }
        }
    }

    private fun compressImage() {
        viewModelScope.launch {
            val uri = _state.value.tempUri ?: return@launch
            compressImage(uri.toString())?.let { array ->
                _state.update { it.copy(compressedBytes = array) }
            }
        }
    }
}