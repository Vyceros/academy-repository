package com.example.baseandroidproject.data.user.mappers

import com.example.baseandroidproject.data.user.models.entities.UserEntity
import com.example.baseandroidproject.data.user.models.dto.UserResponseDto
import com.example.baseandroidproject.domain.models.user.UserResponse

fun UserResponseDto.UserDto.toUserEntity() : UserEntity {
    return UserEntity(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}

fun UserResponse.User.toUserEntity() : UserEntity {
    return UserEntity(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}

fun UserEntity.toUser() : UserResponse.User {
    return UserResponse.User(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}