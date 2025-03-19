package com.example.baseandroidproject.domain.usecases

import com.example.baseandroidproject.domain.abstractions.CategoriesRepository
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CategoriesUseCase {
    suspend operator fun invoke(query: String = ""): Flow<Resource<List<Category>>>
}

class CategoriesUseCaseImpl @Inject constructor(
    private val repo: CategoriesRepository
) : CategoriesUseCase {
    override suspend fun invoke(query: String): Flow<Resource<List<Category>>> {
        return repo.getCategories().map { categories ->
            if (query.isEmpty() || categories !is Resource.Success) {
                categories
            } else if (categories is Resource.Success) {
                val filtered = filterCategories(categories = categories.data, query = query)
                Resource.Success(data = filtered)
            } else {
                categories
            }
        }
    }

    private fun filterCategories(
        categories: List<Category>,
        query: String
    ): List<Category> {
        return categories.mapNotNull { category ->
            val matches = category.name.contains(query, ignoreCase = true)
            val filteredChildren = filterCategories(category.children, query)

            if (matches || filteredChildren.isNotEmpty()) {
                category.copy(children = filteredChildren)
            } else {
                null
            }
        }
    }


}