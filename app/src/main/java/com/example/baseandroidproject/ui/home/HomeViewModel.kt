package com.example.baseandroidproject.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.baseandroidproject.data.repositories.user_repository.UserRepository

class HomeViewModel(private val userRepository: UserRepository) : ViewModel() {
    val items = userRepository.getUsers().cachedIn(viewModelScope)

}