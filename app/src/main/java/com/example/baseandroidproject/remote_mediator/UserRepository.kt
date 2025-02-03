package com.example.baseandroidproject.remote_mediator

import com.example.baseandroidproject.persistence.local.UserDao
import com.example.baseandroidproject.persistence.remote.UserService
import com.example.baseandroidproject.utils.mapToEntity

class UserRepository(
    private val userService: UserService,
    private val userDao: UserDao
) {
    //try to get data from internet and put it here
    suspend fun remoteToLocal() {
        val apiResponseData = userService.getUsers()
        val userEntities = apiResponseData.users.map { it.mapToEntity() }
        userDao.upsertUsers(userEntities)
    }

    fun retrieveUsers() = userDao.getAllUsers()
}