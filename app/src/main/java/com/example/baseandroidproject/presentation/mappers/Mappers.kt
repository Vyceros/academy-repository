package com.example.baseandroidproject.presentation.mappers

import com.example.baseandroidproject.data.remote.models.story.StoryDto
import com.example.baseandroidproject.presentation.models.Story


fun StoryDto.toPresentation() : Story{
    return Story(
        id = id,
        cover = cover,
        title = title
    )
}