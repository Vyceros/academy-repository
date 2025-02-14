package com.example.baseandroidproject.presentation.carousel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.remote.models.ImageCardDto
import com.example.baseandroidproject.data.resource.Resource
import com.example.baseandroidproject.domain.abstractions.ImagesCarouselRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarouselViewModel @Inject constructor(private val imageRepository: ImagesCarouselRepository) : ViewModel(){
        private val _images = MutableStateFlow<Resource<List<ImageCardDto>>>(Resource.Loading())
        val images = _images.asStateFlow()

    init {
        fetchImages()
    }

    private fun fetchImages() {
        viewModelScope.launch {
            try {
                imageRepository.fetchImages().collect { result ->
                    _images.value = result
                }
            } catch (e: Exception) {
                _images.value = Resource.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }

}