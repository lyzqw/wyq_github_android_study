package com.yuwq.libs_common

import android.os.Bundle
import android.util.Log

abstract class BaseStatEvent {

    private val commonBundle = Bundle()

    private fun putStringToBundle(key: String, value: String) {
        commonBundle.putString(key, value)
    }

    fun send() {

        Log.d("stat", "send: $commonBundle")
    }

    inner class Param(private val key: String) {
        private var value: Any? = null
            set(newValue) {
                when (newValue) {
                    is IEventValue -> putStringToBundle(key, newValue.value)
                    else -> putStringToBundle(key, newValue.toString())
                }

                field = newValue
            }

        infix fun to(newValue: Any?): BaseStatEvent {
            this.value = newValue
            return this@BaseStatEvent
        }
    }

}