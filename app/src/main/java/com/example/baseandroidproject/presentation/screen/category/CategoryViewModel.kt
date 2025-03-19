package com.example.baseandroidproject.presentation.screen.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.usecases.CategoriesUseCase
import com.example.baseandroidproject.presentation.screen.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCase: CategoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            _uiState.map {
                it.query
            }.debounce(1000L)
                .distinctUntilChanged()
                .collect{
                    loadData(it)
                }
        }
        loadData("")
    }

    fun onEvent(event : CategoryEvents){
        when(event){
            is CategoryEvents.Load -> {
                loadData(_uiState.value.query)
            }
            is CategoryEvents.onSearch -> {
                _uiState.update { it.copy(query = event.searchQuery) }
            }
        }
    }

    private fun loadData(query : String){
        viewModelScope.launch(Dispatchers.IO) {
            useCase(query).onStart {
                _uiState.update { it.copy(isLoading = true)}
            }.catch {
                _uiState.update { it.copy(isLoading = false, error = it.error) }
            }.collect{ state ->
                when(state){
                    is Resource.Error -> {
                        _uiState.update { it.copy(isLoading = false, error = state.message) }
                    }
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update { it.copy(isLoading = false, data = state.data.toUi()) }
                    }
                }

            }
        }
    }
}