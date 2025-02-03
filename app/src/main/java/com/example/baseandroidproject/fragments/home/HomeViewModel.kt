package com.example.baseandroidproject.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.persistence.remote.Resource
import com.example.baseandroidproject.remote_mediator.UserRepository
import com.example.baseandroidproject.utils.InternetChecker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class HomeViewModel(
    private val repository: UserRepository,
    private val internetChecker: InternetChecker
) : ViewModel() {
    private val _apiLoading = MutableStateFlow<Resource?>(null)
    val apiLoading = _apiLoading.asStateFlow()


    private val _onlineStatus = MutableStateFlow(false)
    val onlineStatus = _onlineStatus.asStateFlow()

    init {
        internetCheck()
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.retrieveUsers().collect { users ->
                    if (users.isNotEmpty()) {
                        _apiLoading.value = Resource.Success(users)
                    } else {
                        _apiLoading.value = Resource.Loading
                        repository.remoteToLocal()
                    }
                }
            } catch (e: UnknownHostException) {
                _apiLoading.value = Resource.Error(e.message ?: "check your internet")

            } catch (e: Exception) {
                _apiLoading.value = Resource.Error(e.message ?: "unknown error found")
            }
        }
    }

    private fun internetCheck() {
        val internetStatus = internetChecker.isInternetAvailable()
        _onlineStatus.value = internetStatus
    }
}