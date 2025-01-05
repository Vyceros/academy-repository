package com.example.baseandroidproject.data

object Storage {
    val messageList = mutableListOf<User>()

    fun sendMessage(user : User){
        user.let {
            messageList.add(it)
        }
    }
}