package com.example.baseandroidproject.data.helpers

import com.example.baseandroidproject.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class SafeCall {

    fun <T> apiCall(safeCall : suspend () -> Response<T>) : Flow<Resource<T>> = flow{
        emit(Resource.Loading)
        val response = safeCall()
        if (response.isSuccessful){
            response.body()?.let { body ->
                emit(Resource.Success(body))
            } ?: emit(Resource.Error(message = "No body"))
        }else{
            emit(Resource.Error(message = "Error : ${response.code()} ${response.message()}"))
        }
    }.catch { e ->
        emit(Resource.Error(message = "Network Error : ${e.localizedMessage}", error = e))
    }
}