package com.example.baseandroidproject.presentation.utils.mappers

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object EpochMapper{
    fun format(epoch: Long?): String {
        val formatter = SimpleDateFormat("d MMMM 'at' h:mm a", Locale.getDefault())
        return formatter.format(Date(epoch ?: 1))
    }
}