package com.example.baseandroidproject.data.mappers

import com.example.baseandroidproject.data.models.CategoryDto
import com.example.baseandroidproject.domain.models.Category


fun CategoryDto.toDomain() : Category{
    return Category(
        id = id,
        name = name,
        bglNumber = bglNumber,
        bglVariant = bglVariant,
        createdAt = createdAt,
        nameGerman = nameGerman,
        main = main,
        orderId = orderId ?: 1
    )
}
fun List<CategoryDto>.toDomain(): List<Category> {
    return this.map { it.toDomain() }
}