package com.example.baseandroidproject.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
enum class KeyboardType {
    @SerialName("number")
    Number,
    @SerialName("text")
    Text
}