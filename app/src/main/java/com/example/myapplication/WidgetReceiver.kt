package com.example.myapplication

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget = WorkerWidget()

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)

        // This is the first data fetch to initialize the widget
        //context?.let { launchWorker(context!!) }

        // Made it go twice with a 2 sec' delay so you can see it glitch twice
        MainScope().launch {
            context?.let { launchWorker(context) }
            delay(2000)
            context?.let { launchWorker(context) }
        }
    }

    private fun launchWorker(context: Context) {
        val workRequest = OneTimeWorkRequestBuilder<CustomWorker>().build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }

    class CustomWorker(context: Context, workerParams: WorkerParameters)
        : CoroutineWorker(context, workerParams) {

        override suspend fun doWork(): Result {
            // LITERALLY do nothing
            return Result.success()
        }
    }
}