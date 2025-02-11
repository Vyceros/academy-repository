package com.example.baseandroidproject.ui.view_model_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@Suppress("UNCHECKED_CAST")
class ViewModelFactory<T : ViewModel>(
    private val viewModelFactory: () -> T
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelFactory() as T
    }
}