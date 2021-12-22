package com.example.stravaver1.networking.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeadItem(
    @Json(name = "username")
    val username: String?,
    @Json(name = "profile")
    val avatar: String?,
    @Json(name = "follower_count")
    val countFollowers: Int,
    @Json(name = "friend_count")
    val countFriends: Int,
    val id: Long
)