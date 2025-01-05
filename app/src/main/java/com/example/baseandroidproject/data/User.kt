package com.example.baseandroidproject.data

import java.util.UUID

data class User(
    val id : UUID = UUID.randomUUID(),
    var messageBody : String,
    var messageDate : Long
)
