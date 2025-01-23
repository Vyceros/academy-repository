package com.example.baseandroidproject.client.response_handler

import androidx.lifecycle.ViewModel
import com.example.baseandroidproject.data.error.ErrorResponse
import com.example.baseandroidproject.data.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import retrofit2.Response
import java.net.UnknownHostException

//base class to handle api responses
abstract class ApiResponseHandler : ViewModel() {
    protected suspend fun <T> handleApiCall(apiCall: suspend () -> Response<T>): Flow<ApiResponse<T>> {
        return flow {
            try {
                emit(ApiResponse.Loading())
                val response = apiCall()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ApiResponse.Success(it))
                    } ?: emit(ApiResponse.Error("No data found"))
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorResponse = errorBody?.let { Json.decodeFromString<ErrorResponse>(it) }
                    emit(ApiResponse.Error(errorResponse?.error))
                }
            } catch (ex: UnknownHostException) {
                emit(ApiResponse.Exception("Host not found, check internet connection"))
            } catch (ex: Exception) {
                emit(ApiResponse.Exception(ex.message))
            }
        }
    }
}