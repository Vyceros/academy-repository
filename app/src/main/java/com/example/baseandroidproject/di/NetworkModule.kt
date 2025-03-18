package com.example.baseandroidproject.di

import com.example.baseandroidproject.BuildConfig
import com.example.baseandroidproject.data.service.CategoryService
import com.example.baseandroidproject.data.util.ApiHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun Json() = Json { explicitNulls = false
    ignoreUnknownKeys = true}

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient, json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        return builder.build()
    }

    @Provides
    fun provideService(retrofit : Retrofit) : CategoryService{
        return retrofit.create(CategoryService::class.java)
    }

    @Provides
    fun provideApiHelper() : ApiHelper{
        return ApiHelper()
    }
}