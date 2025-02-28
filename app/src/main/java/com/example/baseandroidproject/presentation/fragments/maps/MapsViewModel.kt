package com.example.baseandroidproject.presentation.fragments.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.remote.models.Resource
import com.example.baseandroidproject.domain.abstractions.LocationRepository
import com.example.baseandroidproject.presentation.models.ScreenState
import com.example.baseandroidproject.presentation.utils.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    private val _screenState = MutableStateFlow(ScreenState())
    val screenState = _screenState.asStateFlow()

    init {
        retrieveLocations()
    }

    private fun retrieveLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLocation().collect { response ->
                _screenState.update { it.copy(isLoading = response is Resource.Loading) }

                if (response is Resource.Success) {
                    _screenState.update {
                        it.copy(markers = response.data?.map { it.toPresentation() } ?: emptyList())
                    }
                }

            }
        }

    }
}