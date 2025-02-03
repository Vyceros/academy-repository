package com.example.baseandroidproject.utils

import android.content.Context
import android.net.ConnectivityManager
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

fun isNetworkConnected(context : Context){
    val status = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}