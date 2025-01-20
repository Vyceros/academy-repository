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
    @Json(name = "laste_message_type")
    val lastMessageType: String
)

data class Message(
    val id: Int,
    val image: String?,
    val owner: String,
    val lastMessage: String,
    val lastActive: String,
    val unreadMessages: Int,
    val isTyping: Boolean,
    val lastMessageType: MessageType
)

fun MessageDto.toMessage(): Message {
    return Message(
        id = id,
        image = image,
        owner = owner,
        lastMessage = lastMessage,
        lastActive = lastActive,
        unreadMessages = unreadMessages,
        isTyping = isTyping,
        lastMessageType = MessageType.parse(lastMessageType)
    )
}




