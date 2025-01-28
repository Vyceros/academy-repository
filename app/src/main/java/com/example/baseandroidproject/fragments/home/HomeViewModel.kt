package com.example.baseandroidproject.fragments.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.baseandroidproject.client.response_handler.ApiResponseHandler
import com.example.baseandroidproject.client.retrofit.RetrofitClient
import com.example.baseandroidproject.fragments.home.home_recycler.UsersPagingSource

class HomeViewModel : ApiResponseHandler() {

    private val apiSource = RetrofitClient.apiService
    val flow = Pager(
        PagingConfig(
            pageSize = 6,
            prefetchDistance = 1,
            initialLoadSize = 6,
            enablePlaceholders = false
        )
    ){
        UsersPagingSource(apiSource)
    }.flow
}