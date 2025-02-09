package com.example.baseandroidproject.helpers

import com.example.baseandroidproject.data.remote.users.UserDto
import com.example.baseandroidproject.data.local.storage.user_list.UserEntity


fun UserDto.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}