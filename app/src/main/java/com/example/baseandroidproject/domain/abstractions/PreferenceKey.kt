package com.example.baseandroidproject.domain.abstractions

interface PreferenceKey<T> {
    val name : String
    val defaultValue : T
}