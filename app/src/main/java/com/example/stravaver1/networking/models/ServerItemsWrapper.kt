package com.example.stravaver1.networking.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerItemsWrapper<T> (
    val items: List<T>
)