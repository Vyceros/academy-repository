package com.example.baseandroidproject.utils

import com.example.baseandroidproject.persistence.local.UserEntity
import com.example.baseandroidproject.persistence.remote.UserDto

fun UserDto.mapToEntity(): UserEntity {
    return UserEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        about = about ?: "",
        activationStatus = activationStatus.toString(),
        avatar = avatar
    )
}
