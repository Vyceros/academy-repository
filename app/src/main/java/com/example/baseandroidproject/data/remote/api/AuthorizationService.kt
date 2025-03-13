package com.example.baseandroidproject.data.remote.api

import com.example.baseandroidproject.data.remote.models.auth.AuthRequestDto
import com.example.baseandroidproject.data.remote.models.auth.AuthResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationService {

    @POST("login")
    suspend fun loginUser(@Body requestDto: AuthRequestDto): Response<AuthResponseDto>

    @POST("register")
    suspend fun registerUser(@Body requestDto: AuthRequestDto): Response<AuthResponseDto>
}