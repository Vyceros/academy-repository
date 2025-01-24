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
                    }
                } else {
                    emit(ApiResponse.Error(response.parseResponse()))
                }
            } catch (ex: UnknownHostException) {
                emit(ApiResponse.Exception("Host not found, check internet connection"))
            } catch (ex: Exception) {
                emit(ApiResponse.Exception(ex.message))
            }
        }
    }
}


private fun Response<*>.parseResponse(): String? {
    val errorString = this.errorBody()?.string()
    val parser = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }
    return errorString?.let { parser.decodeFromString<ErrorResponse>(it).error }
}
