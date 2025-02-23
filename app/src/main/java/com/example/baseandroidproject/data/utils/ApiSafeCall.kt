package com.example.baseandroidproject.data.utils

import com.example.baseandroidproject.data.remote.models.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ApiSafeCall {

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Flow<Resource<T>> {
        return flow {
            emit(Resource.Loading())
            val response = call()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                emit(Resource.Success(body))
            } else {
                val errorMessage = try {
                    response.parseResponse()
                } catch (e: Exception) {
                    "Error: ${response.code()} ${response.message()}"
                }
                emit(Resource.Error(errorMessage))
            }
        }.catch { e ->
            when (e) {
                is IOException -> emit(Resource.Error(e.message ?: "Network Error"))
                is HttpException -> emit(Resource.Error(e.message ?: "Bad Request"))
                else -> emit(Resource.Error(e.message ?: "Unknown Error"))
            }
        }
    }


    private fun Response<*>.parseResponse(): String {
        val errorString = this.errorBody()?.string()
        return errorString?.let { json.decodeFromString<ErrorResponse>(it).error }
            ?: "Unknown error occurred"
    }
}
