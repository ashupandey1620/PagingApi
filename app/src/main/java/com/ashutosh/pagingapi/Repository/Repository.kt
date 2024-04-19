package com.ashutosh.pagingapi.Repository

import android.util.Log
import com.ashutosh.pagingapi.API.PagingApi
import com.ashutosh.pagingapi.Model.PostResponse
import javax.inject.Inject

class Repository @Inject constructor(private val pagingApi : PagingApi) {


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

}