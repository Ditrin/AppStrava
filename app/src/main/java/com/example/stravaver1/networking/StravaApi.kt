package com.example.stravaver1.networking

import com.example.stravaver1.networking.models.CommentItem
import com.example.stravaver1.networking.models.HeadItem
import retrofit2.http.GET

interface StravaApi {

    @GET("athlete")
    suspend fun getActivity(): HeadItem

    @GET("athlete/activities")
    suspend fun getComments(
    ): List<CommentItem>

}