package com.example.baseandroidproject.helpers

import com.example.baseandroidproject.data.users.UserDto
import com.example.baseandroidproject.storage.user_list.UserEntity


fun UserDto.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}