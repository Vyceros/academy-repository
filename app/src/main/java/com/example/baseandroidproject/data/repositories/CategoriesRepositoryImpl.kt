package com.example.baseandroidproject.data.repositories

import com.example.baseandroidproject.data.mappers.mapFlow
import com.example.baseandroidproject.data.mappers.toDomain
import com.example.baseandroidproject.data.service.CategoryService
import com.example.baseandroidproject.data.util.ApiHelper
import com.example.baseandroidproject.domain.abstractions.CategoriesRepository
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val service: CategoryService,
    private val apiHelper: ApiHelper
) : CategoriesRepository {
    override suspend fun getCategories(query: String): Flow<Resource<List<Category>>> {
        return apiHelper.apiCall {
            service.getCategories(query)
        }.mapFlow { dto ->
            dto.toDomain()
        }
    }
}