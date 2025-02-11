package com.example.baseandroidproject.data.local.repos

import com.example.baseandroidproject.data.local.storage.user_details.TokenDao
import com.example.baseandroidproject.data.local.storage.user_details.UserToken

class UserDetailsRepository(private val userDao: TokenDao) {
    suspend fun getUserDetails(): UserToken? {
        return userDao.getUserToken()
    }

    suspend fun insertUserDetails(userToken: UserToken) {
        userDao.insertUserToken(userToken)

    }

    suspend fun deleteUserDetails() {
        userDao.clearToken()
    }
}