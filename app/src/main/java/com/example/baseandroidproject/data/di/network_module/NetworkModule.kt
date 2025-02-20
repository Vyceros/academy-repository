package com.example.baseandroidproject.data.di.network_module

import android.content.Context
import com.example.baseandroidproject.BuildConfig
import com.example.baseandroidproject.data.helpers.ApiResponseHandler
import com.example.baseandroidproject.data.remote.api.AuthorizationService
import com.example.baseandroidproject.data.remote.api.UserService
import com.example.baseandroidproject.data.remote.connection_observer.InternetObserverImpl
import com.example.baseandroidproject.domain.abstractions.InternetObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

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
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun provideAuthorizationService(retrofit: Retrofit): AuthorizationService {
        return retrofit.create(AuthorizationService::class.java)
    }

    @Provides
    fun provideApiHelper(): ApiResponseHandler {
        return ApiResponseHandler()
    }

    @Provides
    fun provideInternetConnectivityManager(@ApplicationContext context: Context): InternetObserver {
        return InternetObserverImpl(context)
    }
}
