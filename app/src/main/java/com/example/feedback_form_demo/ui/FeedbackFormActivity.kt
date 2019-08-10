package com.example.feedback_form_demo.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.feedback_form_demo.R
import kotlinx.android.synthetic.main.activity_feedback_form.*

class FeedbackFormActivity : AppCompatActivity() {
    companion object {
        private var isRunning = false

        fun start(context: Context) {
            if(isRunning) {
                return
            }

            Intent(context, FeedbackFormActivity::class.java)
                .let {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(it)
                }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isRunning = true

        setContentView(R.layout.activity_feedback_form)

        sendButton.setOnClickListener {
            Toast.makeText(this, R.string.fakeSendText, Toast.LENGTH_SHORT).show()
            finish()
        }

        cancelButton.setOnClickListener { finish() }
    }

    override fun onDestroy() {
        super.onDestroy()

        if(isFinishing) {
            isRunning = false
        }
    }
}
