package com.example.stravaver1.networking.models

object UsersContract {

    const val TABLE_NAME = "users"

    object Columns {
        const val ID = "id"
        const val USERNAME = "username"
        const val PROFILE = "profile"
        const val FOLLOWERS = "follower_count"
        const val FRIENDS = "friend_count"
    }
}