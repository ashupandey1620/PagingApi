package com.ashutosh.pagingapi.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ashutosh.pagingapi.Model.PostResponseItem
import com.ashutosh.pagingapi.Repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject  constructor(
    private val repository: Repository) : ViewModel(){

    private val _getPosts = MutableLiveData<List<PostResponseItem>>()
    val getPosts: LiveData<List<PostResponseItem>> = _getPosts




}