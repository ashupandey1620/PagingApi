package com.ashutosh.pagingapi.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ashutosh.pagingapi.Model.PostData
import com.ashutosh.pagingapi.Model.RemoteKeys
import com.ashutosh.pagingapi.data.API.PagingApi
import com.ashutosh.pagingapi.data.dao.PagingDatabase

@ExperimentalPagingApi
class PagingRemoteMediator(
    private val pagingApi : PagingApi,
    private val pagingDatabase: PagingDatabase
) : RemoteMediator<Int , PostData>() {

    private val pagingDataDao = pagingDatabase.pagingDataDao()
    private val pagingRemoteKeysDao = pagingDatabase.remoteKeysDao()
    override suspend fun load(
        loadType: LoadType ,
        state: PagingState<Int , PostData>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = pagingApi.getAllPosts(page = currentPage, perPage = 10)
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            pagingDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pagingDataDao.deleteAllPosts()
                    pagingRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.map { data ->
                    RemoteKeys(
                        id = data.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                pagingRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                pagingDataDao.addPosts(response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PostData>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                pagingRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, PostData>
    ): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { item ->
                pagingRemoteKeysDao.getRemoteKeys(id = item.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PostData>
    ): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { item ->
                pagingRemoteKeysDao.getRemoteKeys(id = item.id)
            }
    }

}


