package com.example.baseandroidproject.domain.mappers

import com.example.baseandroidproject.data.local.entities.UserEntity
import com.example.baseandroidproject.data.remote.models.users.UserDto
import com.example.baseandroidproject.domain.models.User

fun UserDto.toUserDomain() : User{
    return User(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}

fun UserDto.toUserEntity() : UserEntity {
    return UserEntity(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}