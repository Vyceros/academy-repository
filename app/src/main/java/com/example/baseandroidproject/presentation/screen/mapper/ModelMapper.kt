package com.example.baseandroidproject.presentation.screen.mapper

import com.example.baseandroidproject.domain.models.Category
import com.example.baseandroidproject.presentation.screen.models.CategoryIndicator
import com.example.baseandroidproject.presentation.screen.models.CategoryUi


fun List<Category>.toUi(): List<CategoryUi> =
    flatMap { category ->
        listOf(
            CategoryUi(
                id = category.id,
                name = category.name,
                children = List(category.childPosition) { CategoryIndicator() }
            )
        ) + category.children.toUi()
    }