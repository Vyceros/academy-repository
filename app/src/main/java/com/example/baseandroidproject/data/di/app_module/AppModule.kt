package com.example.baseandroidproject.data.di.app_module

import com.example.baseandroidproject.data.abstractions.PostRepository
import com.example.baseandroidproject.data.abstractions.StoriesRepository
import com.example.baseandroidproject.data.repositories.PostsRepositoryImpl
import com.example.baseandroidproject.data.repositories.StoriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindStoryRepository(storyRepositoryImpl: StoriesRepositoryImpl): StoriesRepository

    @Binds
    abstract fun bindsPostRepository(postRepositoryImpl : PostsRepositoryImpl) : PostRepository
}