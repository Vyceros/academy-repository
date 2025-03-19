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
            when {
                categories !is Resource.Success -> categories
                else -> {
                    val queryFiltered = if (query.isEmpty()) {
                        categories.data
                    } else {
                        filterCategories(categories.data, query)
                    }

                    val depthFiltered = filterMaxDepth(queryFiltered, 4)
                    Resource.Success(data = depthFiltered)
                }
            }
        }
    }

    private fun filterCategories(
        categories: List<Category>,
        query: String
    ): List<Category> {
        return categories.mapNotNull { category ->
            val filter = category.name.contains(query, ignoreCase = true)
            val filteredChildren = filterCategories(category.children, query)

            if (filter || filteredChildren.isNotEmpty()) {
                category.copy(children = filteredChildren)
            } else {
                null
            }
        }
    }

    private fun filterMaxDepth(
        categories: List<Category>,
        maxDepth: Int
    ): List<Category> {
        return categories.mapNotNull { category ->
            if (category.childPosition > maxDepth) {
                null
            } else {
                val filteredChildren = if (category.childPosition == maxDepth) {
                    emptyList()
                } else {
                    filterMaxDepth(category.children, maxDepth)
                }
                category.copy(children = filteredChildren)
            }
        }
    }


}