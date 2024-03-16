package com.qwlyz.androidstudy

import android.app.Application
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.PathUtils
import com.blankj.utilcode.util.Utils
import com.tencent.mars.xlog.Log
import com.tencent.mars.xlog.Xlog
import com.tencent.matrix.Matrix
//import com.tencent.matrix.iocanary.IOCanaryPlugin
//import com.tencent.matrix.iocanary.config.IOConfig


/**
 * @author lyz
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        initXlog()
        initMatrix()
    }

    private fun initMatrix() {
        val builder: Matrix.Builder = Matrix.Builder(this) // build matrix

        builder.pluginListener(TestPluginListener(this)) // add general pluginListener

//        val dynamicConfig = DynamicConfigImplDemo() // dynamic config

//        val ioCanaryPlugin = IOCanaryPlugin(
//            IOConfig.Builder()
//                .dynamicConfig(dynamicConfig)
//                .build()
//        )

//        builder.plugin(ioCanaryPlugin)


        Matrix.init(builder.build())

//        ioCanaryPlugin.start()
    }

    private fun initXlog() {
        val logPath = PathUtils.getInternalAppDataPath()+"/xlog"
        val cachePath = PathUtils.getInternalAppCachePath() + "/xlog/mmap"
        FileUtils.createOrExistsDir(logPath)
        FileUtils.createOrExistsDir(cachePath)
        android.util.Log.i("test", logPath)
        Xlog.open(
            true,
            Xlog.LEVEL_DEBUG,
            Xlog.AppednerModeAsync,
            cachePath,
            logPath,
            "xlog", ""
        )
        Log.setConsoleLogOpen(BuildConfig.DEBUG)
        Log.setLogImp(Xlog())
    }
}