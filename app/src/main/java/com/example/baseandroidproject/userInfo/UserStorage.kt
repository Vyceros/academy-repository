package com.example.baseandroidproject.userInfo


class UserStorage {
    private val listOfUsers = mutableListOf<User>()
    private var deletedCount = 0

    fun addUser(user: User): Boolean {
        val alreadyExistingUser =
            listOfUsers.find { it.email.uppercase() == user.email.uppercase() }
        if (alreadyExistingUser == null) {
            listOfUsers.add(user)
            return true
        }
        return false
    }

    fun removeUser(email: String): Boolean {
        val userToDelete = listOfUsers.find { it.email.uppercase() == email.uppercase() }
        if (userToDelete != null) {
            listOfUsers.remove(userToDelete)
            deletedCount++
            return true
        }
        return false
    }

    fun updateUser(userToUpdateId: String, user: User): Boolean {
        val userToUpdate =
            listOfUsers.find { it.email.uppercase() == userToUpdateId.uppercase() }
        val indexInList = listOfUsers.indexOf(userToUpdate)

        if (userToUpdate != null) {
            listOfUsers[indexInList] = user
            return true
        }
        return false
    }

    fun updateCount() : String
    {
        return " Active users:${listOfUsers.size}\n" +
                "Deleted users:$deletedCount"
    }

}