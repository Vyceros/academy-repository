package com.example.baseandroidproject.data.remote.api

import com.example.baseandroidproject.data.remote.models.users.UserList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<UserList>
}