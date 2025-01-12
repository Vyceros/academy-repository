package com.example.baseandroidproject.data

import java.util.UUID

data class Order(
    val id : UUID = UUID.randomUUID(),
    val name : String,
    val color : String,
    val quantity : Int,
    val status : OrderStatus,
    val price : Double,
    var orderReview : String?
)
