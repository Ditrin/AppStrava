package com.example.stravaver1

import android.app.Application
import timber.log.Timber
import com.example.stravaver1.networking.bd.DataBase


class AppStrava : Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        DataBase.init(this)
    }
}