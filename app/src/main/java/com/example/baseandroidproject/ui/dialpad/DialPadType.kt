package com.example.baseandroidproject.ui.dialpad

sealed class DialPadType {
    data class Number(val value : String) : DialPadType()
    data object FingerPrint : DialPadType()
    data object Backspace : DialPadType()
}