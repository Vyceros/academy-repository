package com.example.baseandroidproject.client.retrofit

import com.example.baseandroidproject.client.services.AuthorizationService
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://reqres.in"
    private const val CONTENT_TYPE = "application/json"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory(MediaType.get(CONTENT_TYPE)))
        .build()

    val apiService = retrofit.create(AuthorizationService::class.java)
}