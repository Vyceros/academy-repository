package com.example.baseandroidproject.sessions

import android.content.Context

class UserSessions(context: Context) {
    companion object {
        private const val NAME = "SESSION"
        private const val TOKEN = "token"
    }

    private val sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    fun addToSession(token: String?) {
        sharedPreferences.edit().apply {
            putString(TOKEN, token)
            apply()
        }
    }

    fun returnToken(): String? = sharedPreferences.getString(TOKEN, null)

    fun clearSession() = sharedPreferences.edit().clear().apply()

}