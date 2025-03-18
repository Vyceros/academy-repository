package com.example.baseandroidproject.data.service

import com.example.baseandroidproject.data.models.CategoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryService {
    @GET("21b20bdf-2d29-4c12-8eec-52573ea70da2")
    suspend fun getCategories(
        @Query("name") name: String
    ): Response<List<CategoryDto>>
}