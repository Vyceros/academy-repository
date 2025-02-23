package com.example.baseandroidproject.data.repositories

import com.example.baseandroidproject.data.abstractions.PostRepository
import com.example.baseandroidproject.data.remote.models.post.PostDto
import com.example.baseandroidproject.data.remote.models.resource.Resource
import com.example.baseandroidproject.data.remote.services.PostsService
import com.example.baseandroidproject.data.utils.ApiSafeCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postService: PostsService,
    private val apiSafeCall: ApiSafeCall
) : PostRepository {

    override suspend fun fetchPosts(): Flow<Resource<List<PostDto>>> {
        return apiSafeCall.apiCall {
            postService.getPosts()
        }
    }
}