package com.example.baseandroidproject.data.passcode

import java.util.UUID

data class PasscodeCircle(
    val id : UUID = UUID.randomUUID(),
    val fillState : Boolean = false
)