package com.example.baseandroidproject.data.utils.mappers

import com.example.baseandroidproject.data.remote.models.auth.AuthResponseDto
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun <T, D> Flow<Resource<T>>.mapFlow(transform: (T) -> D): Flow<Resource<D>> =
    this.map { resource ->
        when (resource) {
            is Resource.Success -> Resource.Success(transform(resource.data))
            is Resource.Error -> Resource.Error(resource.message, resource.error)
            is Resource.Loading -> Resource.Loading
        }
    }

fun AuthResponseDto.toDomain() : AuthResponse{
    return AuthResponse(
        id = id,
        token = token
    )
}
