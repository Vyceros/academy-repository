package com.example.baseandroidproject.data

import java.util.UUID

data class User(
    val id : UUID = UUID.randomUUID(),
    val name : String,
    val messageBody : String,
    val messageDate : Long
)
