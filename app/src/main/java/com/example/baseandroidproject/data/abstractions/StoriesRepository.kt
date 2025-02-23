package com.example.baseandroidproject.data.abstractions

import com.example.baseandroidproject.data.remote.models.resource.Resource
import com.example.baseandroidproject.data.remote.models.story.StoryDto
import kotlinx.coroutines.flow.Flow

interface StoriesRepository {
    suspend fun fetchStories() : Flow<Resource<List<StoryDto>>>
}