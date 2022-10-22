package com.noreplypratap.randomuser.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationHilt : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}
