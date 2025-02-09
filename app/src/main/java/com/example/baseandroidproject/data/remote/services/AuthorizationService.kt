package com.example.baseandroidproject.data.remote.services

import com.example.baseandroidproject.data.remote.services.login.LoginRequest
import com.example.baseandroidproject.data.remote.services.login.LoginResponse
import com.example.baseandroidproject.data.remote.services.register.RegisterRequest
import com.example.baseandroidproject.data.remote.services.register.RegisterResponse
import com.example.baseandroidproject.data.remote.users.UserPagedResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthorizationService {

    @POST("/api/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("/api/login")
    suspend fun login(@Body loginRequest : LoginRequest) : Response<LoginResponse>

    @GET("/api/users")
    suspend fun getUsers(@Query("page") page : Int,
                         @Query ("per_page") perPage : Int) : Response<UserPagedResponse>


}