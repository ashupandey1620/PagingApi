package com.ashutosh.pagingapi.data.Repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.RemoteMediator
import com.ashutosh.pagingapi.Model.PostData
import com.ashutosh.pagingapi.data.API.PagingApi
import com.ashutosh.pagingapi.Model.PostResponse
import com.ashutosh.pagingapi.data.API.SearchPagingSource
import com.ashutosh.pagingapi.data.dao.PagingDatabase
import com.ashutosh.pagingapi.data.paging.PagingRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val pagingApi : PagingApi,
                                     private val pagingDatabase: PagingDatabase
) {


    suspend fun getPosts(): PostResponse? {
        return try {
            val response = pagingApi.posts()

            Log.d("GET PROFILE DATA RESPONSE",response.toString())

            response.body()

        } catch (e: Exception) {

            Log.d("DINANATH",e.toString())
            null
        }
    }


    @OptIn(ExperimentalPagingApi::class)
    fun getAllPosts(): Flow<PagingData<PostData>> {
        val pagingSourceFactory = { pagingDatabase.pagingDataDao().getAllPosts() }
        return Pager(
            config = PagingConfig(pageSize = 10) ,
            remoteMediator = PagingRemoteMediator(pagingApi,
                 pagingDatabase) ,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun searchImages(query: String): Flow<PagingData<PostData>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchPagingSource(pagingApi, query = query)
            }
        ).flow
    }




}