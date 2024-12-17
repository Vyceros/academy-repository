package com.example.baseandroidproject.UserInfo

class UserStorage() {
    private val listOfUsers = mutableListOf<User>()

    fun addUser(user: User) {
        if (!listOfUsers.contains(user)) {
            listOfUsers.add(user)
        }
    }

    fun displayUser(userName : String) : String {
        val user = listOfUsers.find { it.firstName == userName }
        return "User Found : ${user?.firstName} ${user?.lastName} ${user?.email} ${user?.age},"
    }

}