package com.example.baseandroidproject.client

import com.example.baseandroidproject.data.login.LoginRequestDto
import com.example.baseandroidproject.data.login.LoginResponseDto
import com.example.baseandroidproject.data.register.RegisterRequestDto
import com.example.baseandroidproject.data.register.RegisterResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/api/register")
    suspend fun registerUser(@Body register: RegisterRequestDto): Response<RegisterResponseDto>

    @POST("/api/login")
    suspend fun loginUser(@Body login: LoginRequestDto): Response<LoginResponseDto>
}