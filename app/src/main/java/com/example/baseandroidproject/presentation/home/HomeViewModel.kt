package com.example.baseandroidproject.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.abstractions.PostRepository
import com.example.baseandroidproject.data.abstractions.StoriesRepository
import com.example.baseandroidproject.data.remote.models.post.PostDto
import com.example.baseandroidproject.data.remote.models.resource.Resource
import com.example.baseandroidproject.data.remote.models.story.StoryDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storyRepository: StoriesRepository,
    private val postRepository: PostRepository
) : ViewModel() {

    private val _posts = MutableStateFlow<Resource<List<PostDto>>?>(null)
    val posts = _posts.asStateFlow()

    private val _stories = MutableStateFlow<Resource<List<StoryDto>>?>(null)
    val stories = _stories.asStateFlow()

    init {
        fetchPosts()
        fetchStories()
    }

    private fun fetchPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            postRepository.fetchPosts().collect {
                _posts.value = it
            }
        }
    }

    private fun fetchStories() {
        viewModelScope.launch(Dispatchers.IO) {
            storyRepository.fetchStories().collect {
                _stories.value = it
            }
        }
    }
}