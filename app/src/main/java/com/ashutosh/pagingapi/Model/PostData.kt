package com.ashutosh.pagingapi.Model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DATA_TABLE")
data class PostData (
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val body: String,
    val title: String,
    val userId: Int)




