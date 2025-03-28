package com.example.baseandroidproject.data.util

import com.example.baseandroidproject.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class SafeCall {
    suspend fun<T> safeCall(call : suspend () -> T) : Flow<Resource<T>> = flow {
        emit(Resource.Loading)
        try{
            val response = call()
            emit(Resource.Success(response))
        }catch (ex : Throwable){
            emit(Resource.Error(ex.localizedMessage ?: ""))
        }
    }.catch { e ->
        emit(Resource.Error(e.localizedMessage ?: ""))
    }
}