package com.example.baseandroidproject.data

import java.util.UUID

data class Card(
    val id : String = UUID.randomUUID().toString(),
    val name : String,
    val cardNumber : String,
    val expiryDate : String,
    val cvv : String,
    val cardType : CardType
)
