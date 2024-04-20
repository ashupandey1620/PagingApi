package com.ashutosh.pagingapi.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashutosh.pagingapi.Model.PostResponseItem
import com.ashutosh.pagingapi.data.Repository.Repository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject  constructor(
    private val repository: Repository
) : ViewModel(){

    val getAllPosts = repository.getAllPosts()

    private val _getPosts = MutableLiveData<List<PostResponseItem>>()
    val getPosts: LiveData<List<PostResponseItem>> = _getPosts


    fun getPostsFromNetwork() {
        viewModelScope.launch {
            try {
                val result = repository.getPosts()

                // Map ResponseData of post
                result?.let { responseData ->
                    val posts = responseData.map { data ->
                        PostResponseItem(data.body,
                            data.id,
                            data.title,
                            data.userId)
                    }
                    _getPosts.postValue(posts)
                }
            } catch (e: Exception) {
                Log.d("Error Occurred While inserting or updating the Posts",e.toString())
            }
        }
    }


}