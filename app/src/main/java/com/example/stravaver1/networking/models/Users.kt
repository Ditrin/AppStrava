package com.example.stravaver1.networking.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = UsersContract.TABLE_NAME
)
data class Users(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = UsersContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = UsersContract.Columns.USERNAME)
    val username: String?,
    @ColumnInfo(name = UsersContract.Columns.PROFILE)
    val profile: String?,
    @ColumnInfo(name = UsersContract.Columns.FOLLOWERS)
    val followers: Int,
    @ColumnInfo(name = UsersContract.Columns.FRIENDS)
    val friends: Int
)