package com.example.baseandroidproject.client.response_handler

import androidx.lifecycle.ViewModel
import com.example.baseandroidproject.data.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import retrofit2.Response

//base class to handle api responses
abstract class ApiResponseHandler : ViewModel() {
    protected suspend fun <T> handleApiCall(apiCall: suspend () -> Response<T>): ApiResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiCall()
                val responseMessage = Json.decodeFromString<String>(response.message()).toString()
                if (response.isSuccessful) {
                    response.body()?.let {
                        ApiResponse.Success(it)
                    } ?: ApiResponse.Error(response.code(), responseMessage)
                } else {
                    ApiResponse.Error(response.code(), responseMessage)
                }
            } catch (ex: Exception) {
                ApiResponse.Exception(ex.message)
            }
        }
    }
}