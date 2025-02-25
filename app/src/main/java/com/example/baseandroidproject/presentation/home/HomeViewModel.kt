package com.example.baseandroidproject.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.abstractions.PostRepository
import com.example.baseandroidproject.data.abstractions.StoriesRepository
import com.example.baseandroidproject.data.remote.models.resource.Resource
import com.example.baseandroidproject.presentation.models.ScreenState
import com.example.baseandroidproject.presentation.utils.mappers.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storyRepository: StoriesRepository,
    private val postRepository: PostRepository
) : ViewModel() {

    private val _screenState = MutableStateFlow(ScreenState())
    val screenState = _screenState.asStateFlow()


    init {
        fetchPosts()
        fetchStories()
    }

    private fun fetchPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            postRepository.fetchPosts().collect { response ->
                _screenState.update { it.copy(isLoading = response is Resource.Loading) }

                if (response is Resource.Success) {
                    _screenState.update {
                        it.copy(posts = response.data?.map { post -> post.toPresentation() }
                            ?: emptyList())
                    }
                }
            }
        }
    }

    private fun fetchStories() {
        viewModelScope.launch(Dispatchers.IO) {
            storyRepository.fetchStories().collect { response ->
                _screenState.update { it.copy(isLoading = response is Resource.Loading) }

                if (response is Resource.Success) {
                    _screenState.update {
                        it.copy(stories = response.data?.map { story -> story.toPresentation() }
                            ?: emptyList())
                    }
                }
            }
        }
    }
}