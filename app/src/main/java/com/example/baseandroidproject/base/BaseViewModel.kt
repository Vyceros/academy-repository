package com.example.baseandroidproject.base

import androidx.lifecycle.ViewModel
import com.example.baseandroidproject.data.responses.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

//Base class for view model for generic api calls and errors handling

abstract class BaseViewModel : ViewModel() {
    protected suspend fun <T> apiCall(
        apiCall: suspend () -> Response<T>
    ): ApiResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    response.body()?.let {
                        ApiResponse.Success(it)
                    } ?: ApiResponse.Error("No response")
                } else {
                    val errorBody = response.errorBody()?.string()
                    ApiResponse.Error(errorBody ?: "Unknown error happened")
                }
            } catch (e: Exception) {
                ApiResponse.Error(e.message ?: "Unknown error happened")
            }
        }
    }
}