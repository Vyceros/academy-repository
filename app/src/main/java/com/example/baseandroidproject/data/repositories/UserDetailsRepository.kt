package com.example.baseandroidproject.data.repositories

import com.example.baseandroidproject.storage.user_details.DetailsDao
import com.example.baseandroidproject.storage.user_details.UserDetailsEntity

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