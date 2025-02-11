package com.example.baseandroidproject.data.remote.api

import com.example.baseandroidproject.data.remote.models.auth.AuthRequest
import com.example.baseandroidproject.data.remote.models.auth.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationService {

    @POST("login")
    suspend fun loginUser(@Body loginRequest: AuthRequest): Response<AuthResponse>

    @POST("register")
    suspend fun registerUser(@Body registerRequest: AuthRequest): Response<AuthResponse>
}