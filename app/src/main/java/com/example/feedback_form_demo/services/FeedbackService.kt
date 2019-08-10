package com.example.feedback_form_demo.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.os.IBinder
import android.hardware.SensorManager
import com.example.feedback_form_demo.ui.FeedbackFormActivity

/**
 * Service for showing user's feedback form
 */
class FeedbackService : Service() {
    companion object {
        fun start(appContext: Context) {
            if(isRunning) {
                return
            }

            appContext.startService(Intent(appContext, FeedbackService::class.java))
        }

        fun stop(appContext: Context) {
            if(!isRunning) {
                return
            }
            appContext.stopService(Intent(appContext, FeedbackService::class.java))
        }

        private var isRunning = false
    }

    private lateinit var manager: SensorManager
    private lateinit var sensor: Sensor
    private lateinit var detector: ShakeDetector

    override fun onCreate() {
        super.onCreate()

        isRunning = true

        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        detector = ShakeDetector()
        detector.setOnShakeListener { shakesCount ->
            if(shakesCount == 3) {
                FeedbackFormActivity.start(this)
            }
        }

        manager.registerListener(detector, sensor, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onDestroy() {
        super.onDestroy()

        detector.setOnShakeListener(null)
        manager.unregisterListener(detector)

        isRunning = false
    }

    override fun onBind(intent: Intent): IBinder? = null
}