package com.crypto.app.workers

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.crypto.domain_models.DataResult
import com.crypto.usecases.GetListCoinsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

@HiltWorker
class UpdateCoinsDbWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    val useCase: GetListCoinsUseCase
) : CoroutineWorker(context, params) {

    @SuppressLint("LogNotTimber")
    override suspend fun doWork(): Result {
        val response = withContext(Dispatchers.IO) {
            useCase()
        }

        return when (response) {
            is DataResult.Success -> {
                Log.d(TAG, "coins inserted")
                Result.success()
            }
            is DataResult.Failure -> {
                Log.d(TAG, response.throwable.message.toString())
                Result.retry()
            }
        }
    }

    companion object {
        private const val TAG = "UpdateCoinsDbWorker"
        private const val BACK_OFF_DELAY = 1L

        fun start(context: Context) {
            val worker = PeriodicWorkRequestBuilder<UpdateCoinsDbWorker>(1, TimeUnit.HOURS)
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    BACK_OFF_DELAY,
                    TimeUnit.HOURS
                )
                .build()
            WorkManager.getInstance(context).enqueue(worker)
        }
    }
}