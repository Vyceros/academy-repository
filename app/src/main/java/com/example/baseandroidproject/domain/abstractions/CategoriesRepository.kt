package com.example.baseandroidproject.domain.abstractions

import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getCategories(query : String) : Flow<Resource<List<Category>>>
}