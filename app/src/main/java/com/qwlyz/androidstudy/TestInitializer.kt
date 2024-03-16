package com.qwlyz.androidstudy

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.WorkManager

// Initializes WorkManager.
class TestInitializer : Initializer<String> {
    override fun create(context: Context): String {
        Log.d("Initializer", "TestInitializer: ")
        return "a"
    }
    override fun dependencies(): List<Class<out Initializer<*>>> {
        Log.d("Initializer", "TestInitializer.dependencies: ")

        // No dependencies on other libraries.
        return emptyList()
    }
}