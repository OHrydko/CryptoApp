package com.crypto.core.workers

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit

class LogWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    @SuppressLint("LogNotTimber")
    override suspend fun doWork(): Result {
        Log.d("LogWorker", "test")
        return Result.success()
    }

    companion object {
        fun start(context: Context) {
            val worker = PeriodicWorkRequestBuilder<LogWorker>(15, TimeUnit.MINUTES)
                .build()
            WorkManager.getInstance(context).enqueue(worker)
        }
    }
}