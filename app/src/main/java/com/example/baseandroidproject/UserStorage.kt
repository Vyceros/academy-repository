package com.example.baseandroidproject

import com.example.baseandroidproject.validations.User

class UserStorage {
    private var users = mutableSetOf<User?>()

    fun addUser(user: User?) {
        user.let {
            users.add(it)
        }
    }

    fun getUser(email: String?): User? {
        val user = users.find { it?.email == email }
        return user
    }

    fun getUserCount(): String {
        return "Users: ${users.size}"
    }

    fun userExists(emailToCheck: String): Boolean {
        val user = getUser(emailToCheck)
        return user in users
    }


}