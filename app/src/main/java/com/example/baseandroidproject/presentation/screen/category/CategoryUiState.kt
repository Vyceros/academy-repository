package com.example.baseandroidproject.presentation.screen.category

import com.example.baseandroidproject.presentation.screen.models.CategoryUi

data class CategoryUiState(
    val isLoading : Boolean = false,
    val data : List<CategoryUi> = emptyList(),
    val error : String? = null,
    val query : String = ""
)