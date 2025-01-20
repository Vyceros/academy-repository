package com.example.baseandroidproject.data.models

import com.example.baseandroidproject.data.enums.MessageType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MessageDto(
    val id: Int,
    val image: String?,
    val owner: String,
    @Json(name = "last_message")
    val lastMessage: String,
    @Json(name = "last_active")
    val lastActive: String,
    @Json(name = "unread_messages")
    val unreadMessages: Int,
    @Json(name = "is_typing")
    val isTyping: Boolean,
    @Json(name = "last_message_type")
    val lastMessageType: MessageType = MessageType.TEXT
)

