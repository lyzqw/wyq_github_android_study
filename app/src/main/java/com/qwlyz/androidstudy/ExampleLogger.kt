package com.qwlyz.androidstudy

import com.tencent.matrix.util.MatrixLog

class ExampleLogger(instance: Any?) {


    fun get(key: String, defLong: Long): Long {
        //TODO here return default value which is inside sdk, you can change it as you wish. matrix-sdk-key in class MatrixEnum.
        if (MatrixEnum.clicfg_matrix_trace_fps_report_threshold.name == key) {
            return 10000L
        }
        if (MatrixEnum.clicfg_matrix_resource_detect_interval_millis.name == key) {
            MatrixLog.i(
                DynamicConfigImplDemo.TAG,
                "$key, before change:$defLong, after change, value:2000"
            )
            return 2000
        }
        MatrixLog.i(
            DynamicConfigImplDemo.TAG,
            "$key, before change:$defLong, after change, value:2000"
        )
        MatrixLog.i(
            DynamicConfigImplDemo.TAG,
            "$key, before change:$defLong, after change, value:2000"
        )
        MatrixLog.i(
            DynamicConfigImplDemo.TAG,
            "$key, before change:$defLong, after change, value:2000"
        )
        MatrixLog.i(
            DynamicConfigImplDemo.TAG,
            "$key, before change:$defLong, after change, value:2000"
        )
        MatrixLog.i(
            DynamicConfigImplDemo.TAG,
            "$key, before change:$defLong, after change, value:2000"
        )
        MatrixLog.i(
            DynamicConfigImplDemo.TAG,
            "$key, before change:$defLong, after change, value:2000"
        )
        MatrixLog.i(
            DynamicConfigImplDemo.TAG,
            "$key, before change:$defLong, after change, value:2000"
        )
        MatrixLog.i(
            DynamicConfigImplDemo.TAG,
            "$key, before change:$defLong, after change, value:2000"
        )
        MatrixLog.i(
            DynamicConfigImplDemo.TAG,
            "$key, before change:$defLong, after change, value:2000"
        )
        MatrixLog.i(
            DynamicConfigImplDemo.TAG,
            "$key, before change:$defLong, after change, value:2000"
        )
        MatrixLog.i(
            DynamicConfigImplDemo.TAG,
            "$key, before change:$defLong, after change,11 value:2000"
        )
        return defLong
    }

}
