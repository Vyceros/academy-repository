package com.example.baseandroidproject.client

import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://reqres.in"

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build()

    val authorizationService: UserService = client.create(UserService::class.java)
}

