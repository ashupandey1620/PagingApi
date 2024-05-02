package com.ashutosh.pagingapi.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ashutosh.pagingapi.Model.PostData

@Dao
interface DataDao {

    @Query("SELECT * FROM DATA_TABLE")
    fun getAllPosts(): PagingSource<Int , PostData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPosts(images: List<PostData>)

    @Query("DELETE FROM DATA_TABLE")
    suspend fun deleteAllPosts()

    @Query("DELETE FROM DATA_TABLE WHERE userId= :id")
    fun deleteParticularPosts(id: String)

    @Query("DELETE FROM DATA_TABLE WHERE userId != :id")
    fun deleteOtherPosts(id: String)


}