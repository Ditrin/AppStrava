package com.example.stravaver1.networking.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stravaver1.networking.UserRepository
import com.example.stravaver1.networking.models.CommentItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class UserViewModel : ViewModel(){

    private val repository = UserRepository()

    private val userListLiveData = MutableLiveData<List<CommentItem>>(emptyList())

    private var currentSearchJob: Job? = null

    val userList: LiveData<List<CommentItem>>
        get() = userListLiveData

    fun getId() {
        currentSearchJob?.cancel()
        currentSearchJob = viewModelScope.launch {
            runCatching {
                repository.getComments()
            }.onSuccess {
                userListLiveData.postValue(it)
            }.onFailure {
                Timber.e(it)
                userListLiveData.postValue(null)
            }
        }
    }

}