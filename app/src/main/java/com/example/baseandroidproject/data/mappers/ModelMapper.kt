package com.example.baseandroidproject.data.mappers

import com.example.baseandroidproject.data.models.CategoryDto
import com.example.baseandroidproject.domain.models.Category


fun CategoryDto.toDomain(depth : Int = 0) : Category{
    return Category(
        id = id,
        name = name,
        nameGerman = nameGerman,
        childPosition = depth,
        children = children.map { it.toDomain(depth+1) }
    )
}

fun List<CategoryDto>.toDomain(): List<Category> {
    return this.map { it.toDomain() }
}