package com.example.baseandroidproject.data.utils

import com.example.baseandroidproject.data.local.entities.UserEntity
import com.example.baseandroidproject.data.remote.models.users.UserDto
import com.example.baseandroidproject.domain.models.User

fun UserDto.toUserEntity() : UserEntity {
    return UserEntity(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}