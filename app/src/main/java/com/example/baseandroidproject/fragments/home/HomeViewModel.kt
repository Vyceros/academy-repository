package com.example.baseandroidproject.fragments.home

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.baseandroidproject.client.response_handler.ApiResponseHandler
import com.example.baseandroidproject.client.services.AuthorizationService
import com.example.baseandroidproject.fragments.home.home_recycler.UsersPagingSource

class HomeViewModel(private val apiSource: AuthorizationService) : ApiResponseHandler() {

    val flow = Pager(
        PagingConfig(
            pageSize = 6,
            prefetchDistance = 1,
            initialLoadSize = 6,
            enablePlaceholders = false
        )
    ) {
        UsersPagingSource(apiSource)
    }.flow.cachedIn(viewModelScope)

    companion object{

    }
}