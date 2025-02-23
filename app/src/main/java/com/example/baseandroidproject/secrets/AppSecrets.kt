package com.example.baseandroidproject.secrets

class AppSecrets {
    private val encodedSecret = "TFDSFU@LFZ@2345"

    private fun decodeSecret(): String {
        return encodedSecret.map { char ->
            (char.code - 1).toChar()
        }.joinToString("")
    }

    fun secretCheck(input: String): Boolean {
        val decodedKey = decodeSecret()
        return input == decodedKey
    }
}