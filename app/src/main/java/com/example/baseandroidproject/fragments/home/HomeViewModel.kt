package com.example.baseandroidproject.fragments.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.persistence.remote.Resource
import com.example.baseandroidproject.remote_mediator.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class HomeViewModel(
    private val repository: UserRepository
) : ViewModel() {


    private val _resourceState = MutableStateFlow<Resource>(Resource.Loading)
    val resourceState = _resourceState.asStateFlow()

    private val _onlineStatus = MutableStateFlow(false)
    val onlineStatus = _onlineStatus.asStateFlow()

    init {
        Log.d("HomeViewModel", "HomeViewModel init")
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                repository.retrieveUsers().collect { users ->
                    Log.d("HomeViewModel", "users: $users")
                    if (users.isNotEmpty()) {
                        _resourceState.value = Resource.Success(users)
                    } else {
                        repository.remoteToLocal()
                    }
                }
            } catch (e: UnknownHostException) {
                _resourceState.value = Resource.Error(e.message ?: "check your internet")
            } catch (e: Exception) {
                _resourceState.value = Resource.Error(e.message ?: "unknown error found")
            }
        }
    }

//    private fun loadData() {
//        viewModelScope.launch {
//            try {
//                repository.remoteToLocal()
//
//                repository.retrieveUsers().catch {
//                    _resourceState.value = Resource.Error(it.message ?: "unknown error found")
//                }.collect {
//                    _resourceState.value = Resource.Success(it)
//                }
//            } catch (e: UnknownHostException) {
//                _resourceState.value = Resource.Error(e.message ?: "Check internet connection")
//
//                repository.retrieveUsers().catch {
//                    _resourceState.value = Resource.Error(it.message ?: "unknown error found")
//                }.collect {
//                    _resourceState.value = Resource.Success(it)
//                }
//            }
//        }
//    }

}