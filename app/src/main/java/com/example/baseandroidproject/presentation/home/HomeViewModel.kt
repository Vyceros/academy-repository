package com.example.baseandroidproject.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.baseandroidproject.data.repositories.user_repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(userRepositoryImpl: UserRepositoryImpl) : ViewModel() {
    val items = userRepositoryImpl.getUsers().cachedIn(viewModelScope)
}