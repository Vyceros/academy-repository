package com.example.baseandroidproject.presentation.category

sealed class CategoryEvents {
    data class onSearch(val searchQuery : String) : CategoryEvents()
    data object Load : CategoryEvents()
}