package com.qwlyz.androidstudy

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.android36kr.app.module.common.log.KrLog

/**
 * @author lyz
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
//        KrLog.init(this)
    }
}