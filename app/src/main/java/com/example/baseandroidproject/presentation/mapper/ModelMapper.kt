package com.example.baseandroidproject.presentation.mapper

import com.example.baseandroidproject.domain.models.Category
import com.example.baseandroidproject.presentation.models.CategoryUi

fun Category.toPresentation(): CategoryUi {
    return CategoryUi(
        id = id,
        name = name,
        childPosition = childPosition,
        germanName = nameGerman,
        main = main ?: ""
    )
}

fun List<Category>.toPresentation() : List<CategoryUi>{
    return this.map { it.toPresentation() }
}