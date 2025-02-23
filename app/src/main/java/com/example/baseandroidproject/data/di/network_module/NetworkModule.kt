package com.example.baseandroidproject.data.di.network_module

import com.example.baseandroidproject.BuildConfig
import com.example.baseandroidproject.data.remote.services.PostsService
import com.example.baseandroidproject.data.remote.services.StoryService
import com.example.baseandroidproject.data.utils.ApiSafeCall
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val loggingInt = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInt)
            }
        }.build()
    }

    @Provides
    @Singleton
    fun provideStoryService(retrofit: Retrofit): StoryService {
        return retrofit.create(StoryService::class.java)
    }

    @Provides
    @Singleton
    fun providePostService(retrofit: Retrofit): PostsService {
        return retrofit.create(PostsService::class.java)
    }

    @Provides
    @Singleton
    fun provideSafeCall(): ApiSafeCall {
        return ApiSafeCall()
    }
}