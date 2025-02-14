package com.example.baseandroidproject.domain.abstractions

import kotlinx.coroutines.flow.Flow

interface InternetObserver {
    val isConnected : Flow<Boolean>
}