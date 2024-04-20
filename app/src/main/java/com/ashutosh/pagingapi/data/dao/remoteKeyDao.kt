package com.ashutosh.pagingapi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ashutosh.pagingapi.Model.RemoteKeys

@Dao
interface RemoteKeyDao {

    @Query("SELECT * FROM REMOTE_KEYS_TABLE WHERE id =:id")
    suspend fun getRemoteKeys(id: String): RemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<RemoteKeys>)

    @Query("DELETE FROM REMOTE_KEYS_TABLE")
    suspend fun deleteAllRemoteKeys()
}


