package com.example.baseandroidproject.data

import java.util.UUID

data class Order(
    val orderId : UUID = UUID.randomUUID(),
    val date : Long,
    val trackNumber : String,
    val quantity : Int,
    val totalPrice : Int,
    var status : OrderStatus
)

