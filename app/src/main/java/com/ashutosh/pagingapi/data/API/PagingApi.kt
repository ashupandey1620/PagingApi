package com.ashutosh.pagingapi.data.API



import com.ashutosh.pagingapi.Model.PostData
import com.ashutosh.pagingapi.Model.PostResponse
import com.ashutosh.pagingapi.Model.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PagingApi {
    @Headers("Content-Type: application/json")
    @GET("/posts")
    suspend fun posts(): Response<PostResponse>


    @GET("/posts")
    suspend fun getAllPosts(
        @Query("page") page: Int ,
        @Query("per_page") perPage: Int
    ): List<PostData>


    @GET("/search/photos")
    suspend fun searchPosts(
        @Query("query") query: String,
        @Query("per_page") perPage: Int
    ): SearchResult

}