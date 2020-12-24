package com.qwlyz.androidstudy

import android.app.Application
import com.blankj.utilcode.util.Utils

/**
 * @author lyz
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}