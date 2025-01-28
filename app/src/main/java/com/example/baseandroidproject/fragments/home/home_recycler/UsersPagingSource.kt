package com.example.baseandroidproject.fragments.home.home_recycler

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.baseandroidproject.client.services.AuthorizationService
import com.example.baseandroidproject.data.users.User
import kotlinx.coroutines.delay

class UsersPagingSource(private val apiSource: AuthorizationService) : PagingSource<Int, User>() {

    companion object {
        private const val START_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: START_INDEX
        return try {
            val response = apiSource.getUsers(
                page,
                params.loadSize
            ) //Which page its on and how many items per page
            val usersList = response.body()?.data ?: emptyList()
            val nextKey = if (usersList.isEmpty()) null else page + 1
            delay(2000) //Slight delay to simulate pulling more data than its actually pulling because otherwise, its instant
            LoadResult.Page(
                data = usersList,
                prevKey = if (page == START_INDEX) null else page - 1,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

