package com.tmdb.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Tmdb : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
