package com.android36kr.app.module.common.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 捕获异常处理类
 * <p>
 * Created by zhaozhe on 2017/4/10.
 */
class CrashHandler implements Thread.UncaughtExceptionHandler {
    private final static CrashHandler INSTANCE = new CrashHandler();
    private volatile Thread.UncaughtExceptionHandler mOriginalHandler;
    private volatile boolean mCrashed;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init() {
        mOriginalHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(INSTANCE);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (mCrashed) {
            return;
        }
        mCrashed = true;

        boolean finish = handleException(e);
        if (mOriginalHandler != null || !finish) {
            // Default handle exception
            mOriginalHandler.uncaughtException(t, e);
        }
    }

    private boolean handleException(Throwable e) {
        if (e != null) {
            // Log stack trace
            KrLog.logCrash(getStackTrace(e));
        }
        return false;
    }

    private String getStackTrace(Throwable throwable) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        printWriter.close();
        return writer.toString();
    }
}
