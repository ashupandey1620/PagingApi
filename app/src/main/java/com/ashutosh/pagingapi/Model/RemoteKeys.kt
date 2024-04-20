package com.ashutosh.pagingapi.Model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "REMOTE_KEYS_TABLE")
data class RemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)