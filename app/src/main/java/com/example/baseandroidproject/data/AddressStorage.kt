package com.example.baseandroidproject.data

object AddressStorage {
    val list = mutableListOf<Address>()

    fun generateId() : Int{
        val list = AddressStorage.list
        return if (list.isEmpty()){
            1
        }else{
            list.last().id + 1
        }
    }
}