package com.example.baseandroidproject.domain.usecases.user

import androidx.paging.PagingData
import com.example.baseandroidproject.domain.abstractions.user.UserRepository
import com.example.baseandroidproject.domain.models.user.UserResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repo : UserRepository) {
    operator fun invoke(): Flow<PagingData<UserResponse.User>> {
        return repo.getUsers()
    }
}