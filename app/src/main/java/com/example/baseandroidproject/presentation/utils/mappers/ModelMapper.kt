package com.example.baseandroidproject.presentation.utils.mappers

import com.example.baseandroidproject.data.remote.models.post.PostDto
import com.example.baseandroidproject.data.remote.models.story.StoryDto
import com.example.baseandroidproject.presentation.models.Post
import com.example.baseandroidproject.presentation.models.Story


fun StoryDto.toPresentation() : Story{
    return Story(
        id = id,
        cover = cover,
        title = title
    )
}

fun PostDto.toPresentation() : Post {
    return Post(
        id = id,
        images = images ?: emptyList(),
        title = title,
        comments = comments.toString().plus(" ").plus("Comments"),
        likes = likes.toString().plus(" ").plus("Likes"),
        shareContent = shareContent,
        fullName = owner.firstName.plus(" ").plus(owner.lastName),
        postDate = EpochMapper.format(owner.postDate),
        profilePicture = owner.profile
    )
}