package com.example.baseandroidproject.data.mappers

import com.example.baseandroidproject.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun <T, D> Flow<Resource<T>>.mapFlow(transform: (T) -> D): Flow<Resource<D>> =
    this.map { res ->
        when (res) {
            is Resource.Error -> {
                Resource.Error(message = res.message, error = res.error)
            }

            is Resource.Loading -> {
                Resource.Loading
            }

            is Resource.Success -> {
                Resource.Success(transform(res.data))
            }
        }

    }