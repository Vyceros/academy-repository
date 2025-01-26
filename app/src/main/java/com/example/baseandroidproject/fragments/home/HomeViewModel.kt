package com.example.baseandroidproject.fragments.home

import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.client.response_handler.ApiResponseHandler
import com.example.baseandroidproject.client.retrofit.RetrofitClient
import com.example.baseandroidproject.data.response.ApiResponse
import com.example.baseandroidproject.data.users.UserPagedResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ApiResponseHandler() {
    private val _homeCall = MutableStateFlow<ApiResponse<UserPagedResponse>>(ApiResponse.Loading())
    val homeCall = _homeCall.asStateFlow()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            handleApiCall {
                RetrofitClient.apiService.getUsers(2)
            }.collect { response ->
                _homeCall.value = response
            }
        }
    }
}