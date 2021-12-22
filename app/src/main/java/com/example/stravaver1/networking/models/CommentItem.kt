package com.example.stravaver1.networking.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class CommentItem (

    @Json(name = "name")
    val name : String?,
    @Json(name = "start_date")
    val date : String?,
    @Json(name = "type")
    val type : String?,
    @Json(name = "distance")
    val distance : Float,
    @Json(name = "moving_time")
    val moving_time : Int,
    @Json(name = "kudos_count")
    val kudos_count : Int,
    @Json(name = "comment_count")
    val comment_count : Int,
    val id : Long
)