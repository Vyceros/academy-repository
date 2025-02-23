package com.example.baseandroidproject.data.remote.services

import com.example.baseandroidproject.data.remote.models.post.PostDto
import retrofit2.Response
import retrofit2.http.GET

interface PostsService {
    @GET("1ba8b612-8391-41e5-8560-98e4a48decc7")
    suspend fun getPosts(): Response<List<PostDto>>
}