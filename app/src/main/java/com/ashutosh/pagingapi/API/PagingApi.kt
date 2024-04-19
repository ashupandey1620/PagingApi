package com.ashutosh.pagingapi.API

import com.ashutosh.pagingapi.Model.PostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface PagingApi {

    @Headers("Content-Type: application/json")
    @GET("/posts")
    suspend fun profile(): Response<PostResponse>


}