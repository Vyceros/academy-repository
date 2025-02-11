package com.example.baseandroidproject.data.helpers

import com.example.baseandroidproject.data.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

object ApiResponseHandler {

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    suspend fun <T> apiCall(call: suspend () -> Response<T>): Flow<Resource<T>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = call()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Resource.Success(it))
                    } ?: emit(Resource.Error("Unknown error occurred"))
                } else {
                    emit(Resource.Error(response.parseResponse()))
                }
            } catch (ex: IOException) {
                emit(Resource.Error(ex.message ?: "Network Error"))
            } catch (ex: HttpException) {
                emit(Resource.Error(ex.message ?: "Bad Request"))
            } catch (ex: Throwable) {
                emit(Resource.Error(ex.message ?: "Unknown Error"))
            }
        }
    }

    private fun Response<*>.parseResponse(): String {
        val errorString = this.errorBody()?.string()
        return errorString?.let { json.decodeFromString<ErrorResponse>(it).error }
            ?: "Unknown error occurred"
    }
}