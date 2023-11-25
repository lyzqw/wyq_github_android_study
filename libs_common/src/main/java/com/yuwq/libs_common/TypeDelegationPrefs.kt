package com.yuwq.libs_common

import android.content.SharedPreferences
import kotlin.reflect.KProperty

abstract class TypeDelegationPrefs(
    val prefs: () -> SharedPreferences
) {


    open inner class PrefKey<T : Any>(
        val key: String,
        val default: T
    ) {

        operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
            with(prefs()) {
                return when (default) {
                    is String -> getString(key, "") as T
                    else -> error("")
                }
            }
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {

        }

    }
}