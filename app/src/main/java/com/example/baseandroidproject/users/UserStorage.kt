package com.example.baseandroidproject.users

object UserStorage {
    private val listUsers = mutableListOf<User>()

    fun addUser(user: User): Boolean {
        val alreadyExistingUser =
            listUsers.find { it.id == user.id || it.email.uppercase() == user.email.uppercase() }
        if (alreadyExistingUser == null)
        {
            user.id++
            listUsers.add(user)
            return true
        }
        return false
    }

    fun searchUser(search: String): List<User> {
        val lowerSearch = search.lowercase()
        return listUsers.filter { user ->
            user.firstName.lowercase().contains(lowerSearch)
                    || user.lastName.lowercase().contains(lowerSearch)
                    || user.email.lowercase().contains(lowerSearch)
                    || user.birthday.lowercase().contains(lowerSearch)
                    || user.address.lowercase().contains(lowerSearch)
        }
    }
}