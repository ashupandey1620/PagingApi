package com.ashutosh.pagingapi.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashutosh.pagingapi.Model.PostData
import com.ashutosh.pagingapi.Model.RemoteKeys

@Database(entities = [PostData::class, RemoteKeys::class], version = 1)
abstract class PagingDatabase : RoomDatabase() {
    abstract fun pagingDataDao(): DataDao
    abstract fun remoteKeysDao(): RemoteKeyDao
}

