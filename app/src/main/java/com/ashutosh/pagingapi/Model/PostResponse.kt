package com.ashutosh.pagingapi.Model

class PostResponse : ArrayList<PostResponseItem>()


data class PostResponseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)