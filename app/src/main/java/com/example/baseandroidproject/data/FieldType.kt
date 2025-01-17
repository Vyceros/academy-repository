package com.example.baseandroidproject.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class FieldType {
    @SerialName("input")
    Input,
    @SerialName("chooser")
    Chooser
}