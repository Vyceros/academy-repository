package com.example.baseandroidproject.data.auth.service

import com.example.baseandroidproject.data.auth.dto.AuthRequestDto
import com.example.baseandroidproject.data.auth.dto.AuthResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationService {

    @POST("login")
    suspend fun loginUser(@Body requestDto: AuthRequestDto): Response<AuthResponseDto>

    @POST("register")
    suspend fun registerUser(@Body requestDto: AuthRequestDto): Response<AuthResponseDto>
}