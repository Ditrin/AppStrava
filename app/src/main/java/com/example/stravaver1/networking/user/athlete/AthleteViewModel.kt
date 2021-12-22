package com.example.stravaver1.networking.user.athlete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stravaver1.R
import com.example.stravaver1.networking.UserRepository
import com.example.stravaver1.networking.models.HeadItem
import com.example.stravaver1.networking.models.Users
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AthleteViewModel : ViewModel(){

    private val repository = UserRepository()

    private val userInfoLiveData = MutableLiveData<HeadItem?>(null)

    private val _failToast = MutableLiveData<Int>()

    private var currentInfoJob: Job? = null

    val userInfo: LiveData<HeadItem?>
        get() = userInfoLiveData

    val failToast: LiveData<Int>
        get() = _failToast



    fun getUserInfo(){
        currentInfoJob?.cancel()
        currentInfoJob = viewModelScope.launch {
            runCatching {
                repository.getActivityById()
            }.onSuccess {
                userInfoLiveData.postValue(it)
                val user = Users(
                    id = it.id,
                    username = it.username,
                    profile = it.avatar,
                    followers = it.countFollowers,
                    friends = it.countFriends
                )
                repository.saveUser(user)

            }.onFailure {
                val user = repository.getAllUsers()
                val item = HeadItem(
                    username = user.username,
                    avatar = user.profile,
                    countFollowers = user.followers,
                    countFriends = user.friends,
                    id = user.id
                )
                userInfoLiveData.postValue(item)
//                _failToast.postValue(R.string.push)
//                userInfoLiveData.postValue(null)
            }
        }
    }
}