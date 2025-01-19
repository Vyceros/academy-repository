package com.example.baseandroidproject.base

import androidx.lifecycle.ViewModel
import com.example.baseandroidproject.data.error.ErrorBody
import com.example.baseandroidproject.data.responses.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import retrofit2.Response

//Base class for view model for generic api calls and errors handling
//basically a repository
abstract class ResponseRepository : ViewModel() {
    protected suspend fun <T> apiCall(
        apiCall: suspend () -> Response<T>
    ): ApiResponse<T> {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    response.body()?.let {
                        ApiResponse.Success(it)
                    } ?: ApiResponse.Error("Unknown error happened")
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = if (!errorBody.isNullOrEmpty()) {
                        try {
                            json.decodeFromString<ErrorBody>(errorBody).message
                        } catch (e: Exception) {
                            "Error parsing error body: ${e.message}"
                        }
                    } else {
                        "Empty error body"
                    }
                    ApiResponse.Error(ErrorBody(errorMessage).message)
                }
            } catch (e: Exception) {
                ApiResponse.Error(e.message)
            }
        }
    }
}