package com.example.stravaver1.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

object Networking {

    private val okhttpClient = OkHttpClient.Builder()
    .addInterceptor(AddToken())
    .addInterceptor(HttpLoggingInterceptor {
        Timber.tag("Network").d(it)
    }
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    )
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.strava.com/api/v3/")
        .client(okhttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val stravaApi: StravaApi
    get() = retrofit.create(StravaApi::class.java)
}