package com.qwlyz.androidstudy

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import androidx.work.WorkManager

// Initializes ExampleLogger.
class ExampleLoggerInitializer : Initializer<ExampleLogger> {
    override fun create(context: Context): ExampleLogger {
        // WorkManager.getInstance() is non-null only after
        // WorkManager is initialized.
        Log.d("Initializer", "ExampleLoggerInitializer: ")
        return ExampleLogger(null)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        // Defines a dependency on WorkManagerInitializer so it can be
        // initialized after WorkManager is initialized.
//        return listOf(WorkManagerInitializer::class.java)
        return emptyList()
    }
}