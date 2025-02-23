package com.example.baseandroidproject.data.repositories

import com.example.baseandroidproject.data.abstractions.StoriesRepository
import com.example.baseandroidproject.data.remote.models.resource.Resource
import com.example.baseandroidproject.data.remote.models.story.StoryDto
import com.example.baseandroidproject.data.remote.services.StoryService
import com.example.baseandroidproject.data.utils.ApiSafeCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoriesRepositoryImpl @Inject constructor(
    private val storyService: StoryService,
    private val apiSafeCall: ApiSafeCall
) : StoriesRepository {
    override suspend fun fetchStories(): Flow<Resource<List<StoryDto>>> {
        return apiSafeCall.apiCall {
            storyService.getStories()
        }
    }
}