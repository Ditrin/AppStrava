package com.example.stravaver1.networking

import com.example.stravaver1.networking.bd.DataBase
import com.example.stravaver1.networking.models.CommentItem
import com.example.stravaver1.networking.models.HeadItem
import com.example.stravaver1.networking.models.Users

class UserRepository {

    private val userDao = DataBase.instance.userDao()

    suspend fun saveUser(user: Users) {
        userDao.insertUser(listOf(user))
    }

    suspend fun getAllUsers(): Users {
        return userDao.getAllUsers()
    }

    suspend fun getActivityById(
    ) : HeadItem {
        return Networking.stravaApi.getActivity()
    }

    suspend fun getComments() : List<CommentItem> {
        return Networking.stravaApi.getComments()
    }





}