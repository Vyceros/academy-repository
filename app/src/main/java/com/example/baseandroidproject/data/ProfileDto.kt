package com.example.baseandroidproject.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    @SerialName("field_id")
    val fieldId : Int,
    val hint : String?,
    @SerialName("field_type")
    val fieldType : FieldType?,
    val keyboard : KeyboardType?,
    val required : Boolean?,
    @SerialName("is_active")
    val isActive : Boolean?,
    val icon : String?
)


