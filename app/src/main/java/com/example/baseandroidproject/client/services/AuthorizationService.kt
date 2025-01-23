package com.example.baseandroidproject.client.services

import com.example.baseandroidproject.data.login.LoginRequest
import com.example.baseandroidproject.data.login.LoginResponse
import com.example.baseandroidproject.data.register.RegisterRequest
import com.example.baseandroidproject.data.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationService {

    @POST("api/register/")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("api/login/")
    suspend fun login(@Body loginRequest : LoginRequest) : Response<LoginResponse>

}