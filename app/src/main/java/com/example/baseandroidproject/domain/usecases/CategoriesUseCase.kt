package com.example.baseandroidproject.domain.usecases

import com.example.baseandroidproject.domain.abstractions.CategoriesRepository
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val repo : CategoriesRepository
) {
    suspend operator fun invoke(query : String): Flow<Resource<List<Category>>> {
        return repo.getCategories(query)
    }
}