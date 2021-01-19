package com.qwlyz.androidstudy

import com.google.firebase.analytics.FirebaseAnalytics

/**
 *
 *
        adb shell setprop debug.firebase.analytics.app com.qwlyz.androidstudy

        adb shell setprop log.tag.FA VERBOSE
        adb shell setprop log.tag.FA-SVC VERBOSE
        adb logcat -v time -s FA FA-SVC

        Log.d("wqq", "onBindViewHolder: click")
        val bundle = Bundle()
        bundle.putLong("click_time",System.currentTimeMillis());
        bundle.putString("key","value")
        instance.logEvent("click_event", bundle)

        google 统计

 * @author lyz
 */
object Analytics {

}