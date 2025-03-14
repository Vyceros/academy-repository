package com.example.baseandroidproject.data.user.service

import com.example.baseandroidproject.data.user.models.dto.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UserResponseDto
}