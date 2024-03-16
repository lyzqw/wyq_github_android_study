package com.qwlyz.androidstudy

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.WorkManager

// Initializes WorkManager.
class WorkManagerInitializer : Initializer<WorkManager> {
    override fun create(context: Context): WorkManager {
        Log.d("Initializer", "WorkManager: ")
        val configuration = Configuration.Builder().build()
        WorkManager.initialize(context, configuration)
        return WorkManager.getInstance(context)
    }
    override fun dependencies(): List<Class<out Initializer<*>>> {
        Log.d("Initializer", "WorkManager.dependencies: ")

        // No dependencies on other libraries.
        return emptyList()
    }
}