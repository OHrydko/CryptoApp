package com.crypto.core

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    @SuppressLint("LogNotTimber")
    override fun onCreate() {
        super.onCreate()

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Toast.makeText(this, "${task.exception}", Toast.LENGTH_LONG).show()
                return@OnCompleteListener
            }

            // Get FCM registration token
            val token = task.result
            if (token != null) {
                Log.d("fb token", token)
            }
        })
    }
}