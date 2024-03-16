package com.qwlyz.androidstudy.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.os.Process;
import android.util.Log;

public class DebugCheckUtil {

    private static final String TAG = "DebugCheckUtil";

    public static void checkAndTerminateIfDebugging(Context context) {
        boolean isDebugging = Debug.isDebuggerConnected();
        if (isDebugging) {
            // 终止自身进程
            System.exit(0);
        }
    }
}
