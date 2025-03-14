package com.example.baseandroidproject.domain.abstractions.internet_observer

import kotlinx.coroutines.flow.Flow

interface InternetObserver {
    val isConnected : Flow<Boolean>
}