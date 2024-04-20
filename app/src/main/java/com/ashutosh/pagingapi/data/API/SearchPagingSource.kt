package com.ashutosh.pagingapi.data.API

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ashutosh.pagingapi.Model.PostData

class SearchPagingSource(
    private val pagingApi: PagingApi,
    private val query: String
): PagingSource<Int , PostData>() {
    override fun getRefreshKey(state: PagingState<Int , PostData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int , PostData> {
        val currentPage = params.key ?: 1
        return try {
            val response = pagingApi.searchPosts(query = query, perPage = 10)
            val endOfPaginationReached = response.posts.isEmpty()
            if (response.posts.isNotEmpty()) {
                LoadResult.Page(
                    data = response.posts,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}


