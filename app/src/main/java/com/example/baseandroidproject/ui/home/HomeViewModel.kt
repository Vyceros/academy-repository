package com.example.baseandroidproject.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.baseandroidproject.client.response_handler.ApiResponseHandler
import com.example.baseandroidproject.client.services.AuthorizationService
import com.example.baseandroidproject.storage.ApplicationDatabase
import com.example.baseandroidproject.storage.UserRemoteMediator

class HomeViewModel(
    private val apiSource: AuthorizationService,
    private val database: ApplicationDatabase
) : ApiResponseHandler() {

    @OptIn(ExperimentalPagingApi::class)
    val flow = Pager(
        config = PagingConfig(
            pageSize = 6,
            prefetchDistance = 1,
            initialLoadSize = 6,
            enablePlaceholders = false
        ),
        remoteMediator = UserRemoteMediator(apiSource, database)
    ) {
        database.userDao().pagingSource()
    }.flow.cachedIn(viewModelScope)
}