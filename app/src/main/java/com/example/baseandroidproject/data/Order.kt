package com.example.baseandroidproject.data

data class Order(
    val orderId : Int,
    val date : Long,
    val trackNumber : String,
    val quantity : Int,
    val totalPrice : Int,
    val status : OrderStatus
)

