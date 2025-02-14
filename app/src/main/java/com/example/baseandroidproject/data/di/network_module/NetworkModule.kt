package com.example.baseandroidproject.data.di.network_module

import com.example.baseandroidproject.data.remote.services.ImageService
import com.example.baseandroidproject.data.utils.ApiSafeCallHandler
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

    @Module
    @InstallIn(SingletonComponent::class)
    object NetworkModule {

        @Provides
        fun provideJson(): Json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }

        @Provides
        fun provideRetrofit(httpClient: OkHttpClient, json: Json): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://run.mocky.io/v3/")
                .client(httpClient)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
        }

        @Provides
        fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder().addInterceptor(interceptor).build()
        }

        @Provides
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        @Provides
        fun provideUserService(retrofit: Retrofit): ImageService {
            return retrofit.create(ImageService::class.java)
        }


        @Provides
        fun provideApiHelper(): ApiSafeCallHandler {
            return ApiSafeCallHandler()
        }
    }
}