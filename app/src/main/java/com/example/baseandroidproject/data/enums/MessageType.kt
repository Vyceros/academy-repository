package com.example.baseandroidproject.data.enums


enum class MessageType(name : String) {
    TEXT("text"),
    FILE("file"),
    VOICE("voice");

    companion object{
        fun parse(type: String): MessageType {
            return when (type) {
                "text" -> MessageType.TEXT
                "file" -> MessageType.FILE
                "voice" -> MessageType.VOICE
                else -> {
                    MessageType.TEXT
                }
            }
        }
    }
}