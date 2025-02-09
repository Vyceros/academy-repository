package com.example.baseandroidproject.data.local.repos

import com.example.baseandroidproject.data.local.storage.user_details.DetailsDao
import com.example.baseandroidproject.data.local.storage.user_details.UserDetailsEntity

class UserDetailsRepository(private val userDao: DetailsDao) {
    suspend fun getUserDetails(): UserDetailsEntity? {
        return userDao.getUserDetail()
    }

    suspend fun insertUserDetails(userDetailsEntity: UserDetailsEntity) {
        userDao.insertUserToken(userDetailsEntity)

    }

    suspend fun deleteUserDetails() {
        userDao.clearDetails()
    }
}