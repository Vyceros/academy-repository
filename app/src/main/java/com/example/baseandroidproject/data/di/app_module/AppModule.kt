package com.example.baseandroidproject.data.di.app_module

import com.example.baseandroidproject.data.abstractions.PostRepository
import com.example.baseandroidproject.data.abstractions.StoriesRepository
import com.example.baseandroidproject.data.repositories.PostsRepositoryImpl
import com.example.baseandroidproject.data.repositories.StoriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun bindStoryRepository(storyRepositoryImpl: StoriesRepositoryImpl): StoriesRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsPostRepository(postRepositoryImpl : PostsRepositoryImpl) : PostRepository
}