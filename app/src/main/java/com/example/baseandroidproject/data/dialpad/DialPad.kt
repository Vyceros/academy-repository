package com.example.baseandroidproject.data.dialpad

import java.util.UUID

data class DialPad(
    val id : UUID = UUID.randomUUID(),
    val type : DialPadType,
    val value : String? = null
)