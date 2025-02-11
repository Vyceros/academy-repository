package com.example.baseandroidproject.data.remote

import com.example.baseandroidproject.data.remote.api.AuthorizationService
import com.example.baseandroidproject.data.remote.api.UserService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitImpl {
    private const val BASE_URL = "https://reqres.in/api/"

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val usersService: UserService = getRetrofitInstance().create(UserService::class.java)

    val authorizationService: AuthorizationService =
        getRetrofitInstance().create(AuthorizationService::class.java)

}