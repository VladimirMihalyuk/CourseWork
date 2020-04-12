package com.example.schedule.app

import android.app.Application
import com.example.schedule.di.AppComponent

import com.example.schedule.di.DaggerAppComponent

class ScheduleApplication: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: Application): AppComponent =
        DaggerAppComponent.builder()
            .application(app)
            .build()
}