package com.example.baseandroidproject.persistence.remote

import retrofit2.http.GET

interface UserService {

    @GET("f3f41821-7434-471f-9baa-ae3dee984e6d")
    suspend fun getUsers(): ApiResponse
}