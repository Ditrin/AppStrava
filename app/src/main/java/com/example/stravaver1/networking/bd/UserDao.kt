package com.example.stravaver1.networking.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stravaver1.networking.models.Users
import com.example.stravaver1.networking.models.UsersContract


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: List<Users>)

    @Query("SELECT * FROM ${UsersContract.TABLE_NAME}")
    suspend fun getAllUsers(): Users
}