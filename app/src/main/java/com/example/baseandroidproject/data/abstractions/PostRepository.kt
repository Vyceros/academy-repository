package com.example.baseandroidproject.data.abstractions

import com.example.baseandroidproject.data.remote.models.post.PostDto
import com.example.baseandroidproject.data.remote.models.resource.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun fetchPosts() : Flow<Resource<List<PostDto>>>

}