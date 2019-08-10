package com.example.feedback_form_demo.application

import android.app.Application

class App : Application() {

    private lateinit var activitiesMonitor: ActivitiesMonitor

    override fun onCreate() {
        super.onCreate()

        activitiesMonitor = ActivitiesMonitor(this.applicationContext)
    }
}