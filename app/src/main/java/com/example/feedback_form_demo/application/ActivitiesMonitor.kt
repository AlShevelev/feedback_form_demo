package com.example.feedback_form_demo.application

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.feedback_form_demo.services.FeedbackService

class ActivitiesMonitor(private val appContext: Context): Application.ActivityLifecycleCallbacks {
    private var activeActivities = 0

    init {
        (appContext as Application).registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityPaused(activity: Activity?) {
        activeActivities--

        if(activeActivities == 0) {
            FeedbackService.stop(appContext)
        }
    }

    override fun onActivityResumed(activity: Activity?) {
        activeActivities++

        if(activeActivities > 0) {
            FeedbackService.start(appContext)
        }
    }

    override fun onActivityStarted(p0: Activity) {
        // do nothing
    }

    override fun onActivityDestroyed(p0: Activity) {
        // do nothing
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        // do nothing
    }

    override fun onActivityStopped(p0: Activity) {
        // do nothing
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        // do nothing
    }
}