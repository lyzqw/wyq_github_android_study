package com.android36kr.app.module.common.log;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.qwlyz.androidstudy.BuildConfig;
import com.tencent.mars.xlog.Log;
import com.tencent.mars.xlog.Xlog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * For Mars Xlog
 * <p>
 * Created by zhaozhe on 2017/4/7.
 */
public class KrLog {
    private final static String KR_LOG_DIR = "logs";
    /**
     * 操作系统信息
     */
    private final static String KR_SYS_TAG = "sys";
    /**
     * 应用信息
     */
    private final static String KR_APP_TAG = "app";
    /**
     * 主进程崩溃信息
     */
    private final static String KR_CRASH_TAG = "crash";
    /**
     * 用户操作轨迹信息
     */
    private final static String KR_USER_ACTION_TAG = "ua";

    /**
     * 用户反馈bug收集
     */
    private final static String KR_DEBUG_INFO = "debug_info";

    /**
     * 反馈邮箱
     */
    private final static String EMAIL_RECEIVER = "zhaozhe@36kr.com";
    /**
     * Debug log文件名前缀
     */
    private final static String DEBUG_LOG_FILENAME_PREFIX = "Kr_D";
    /**
     * Release log文件名前缀
     */
    private final static String RELEASE_LOG_FILENAME_PREFIX = "Kr_R";
    /**
     * Zip log文件名
     */
    private final static String LOG_ZIP_FILENAME = "Kr_Z.zip";

    static {
        try {
            System.loadLibrary("stlport_shared");
            System.loadLibrary("marsxlog");
        } catch (Exception e) {
            // https://bugly.qq.com/v2/crash-reporting/crashes/900012309/32108?pid=1
            LogUtils.e(e.toString());
        }
    }

    private KrLog() {
    }

    public static void init(Context context) {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 无SD卡
            return;
        }

        // log dir
        File logDir = context.getExternalFilesDir(KR_LOG_DIR);
        if (logDir == null) {
            return;
        }
        String logPath = logDir.getAbsolutePath();
        // cache dir
        File cacheDir = context.getCacheDir();
        String cachePath = cacheDir == null ? "" : cacheDir.getAbsolutePath();

        LogUtils.d("logPath: "+logPath);
        LogUtils.d("cachePath: "+cachePath);
        if (BuildConfig.DEBUG) {
            Xlog.appenderOpen(Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, cachePath, logPath, DEBUG_LOG_FILENAME_PREFIX);
            Xlog.setConsoleLogOpen(true);
        } else {
            Xlog.appenderOpen(Xlog.LEVEL_INFO, Xlog.AppednerModeAsync, cachePath, logPath, RELEASE_LOG_FILENAME_PREFIX);
            Xlog.setConsoleLogOpen(false);
        }

        Log.setLogImp(new Xlog());

        // Log sys info
        Log.i(KR_SYS_TAG, getSysInfo());
        // Log App info
        Log.i(KR_APP_TAG, getAppInfo());
        // Init crash handler
        //CrashHandler.getInstance().init();
    }

    /**
     * 获取系统信息
     */
    private static String getSysInfo() {
        final StringBuilder sb = new StringBuilder();
        try {
            sb.append(">>> SI "); // sys info
            sb.append(Log.getSysInfo());
            sb.append(" SI <<<");
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 获取应用信息
     */
    private static String getAppInfo() {
        final StringBuilder sb = new StringBuilder();
        try {
            sb.append(">>> AI "); // app info
            sb.append("VN:[") // version name
                    .append(AppUtils.getAppName())
                    .append("]");
            sb.append("VC:[") // version code
                    .append(AppUtils.getAppVersionCode())
                    .append("]");
            sb.append(" AI <<<");
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 记录应用崩溃信息
     */
    static void logCrash(String msg) {
        Log.e(KR_CRASH_TAG, msg);
    }


    /**
     * 记录错误日志
     */
    public static void logDebugInfo(String msg) {
        Log.i(KR_DEBUG_INFO, msg);
    }

    /**
     * 记录用户轨迹 <br/>
     * <b>Notice: 只在BaseActivity和BaseFragment中调用，其它位置慎用</b>
     *
     * @param name 当前页面名
     * @param status 当前页面状态
     */
    public static void logAction(String name, String status) {
        Log.i(KR_USER_ACTION_TAG, "%s#%s", name, status);
    }


    /**
     * 程序退出时调用
     */
    public static void close() {
        Log.appenderClose();
    }

    /**
     * 发邮件
     */
    public static void sendEmail(final Context context, final Callback callback) {
        Observable.just(0)
                .map(integer -> {
                    File rootDir = context.getExternalFilesDir(null);
                    if (rootDir == null) {
                        // get root dir failure
                        return "";
                    }

                    // flush log
                    Log.appenderFlush(true);

                    String zipFile = rootDir + File.separator + LOG_ZIP_FILENAME;
                    File oldZipFile = new File(zipFile);
                    if (oldZipFile.exists()) {
                        //noinspection ResultOfMethodCallIgnored
                        oldZipFile.delete();
                    }

                    File logDir = context.getExternalFilesDir(KR_LOG_DIR);
                    if (logDir != null && logDir.exists()) {
                        // compress ...
                        compressFiles(logDir.getAbsolutePath(), zipFile);
                        // return zip path
                        return zipFile;
                    }
                    return "";
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(path -> {

                    LogUtils.d("path: "+path);
                    LogUtils.d("content: "+FileIOUtils.readFile2String(path));


//                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    String[] receivers = new String[] { EMAIL_RECEIVER };
//                    intent.putExtra(Intent.EXTRA_EMAIL, receivers);
//                    intent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.feedback_email_content));
//                    if (!TextUtils.isEmpty(path)) {
//                        intent.setType("application/extension");
//                        intent.putExtra(Intent.EXTRA_STREAM, FileUtil.file2Uri(context,new File(path)));
//                    }
//                    context.startActivity(
//                            Intent.createChooser(intent, context.getString(R.string.feedback_email_chooser_text)));
//                    if (callback != null) {
//                        callback.finish();
//                    }
                }, error -> {
                    if (BuildConfig.DEBUG) {
                        error.printStackTrace();
                    }
                    if (callback != null) {
                        callback.finish();
                    }
                });
    }

    public interface Callback {
        void finish();
    }

    private static void compressFiles(String dir, String zipFile) {
        File directory = new File(dir);
        int rootEndIndex = directory.getAbsolutePath()
                .length() + 1;
        List<String> paths = getPathList(directory);

        try {
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (String path : paths) {
                // Create a zip entry
                String name = path.substring(rootEndIndex, path.length());
                ZipEntry zipEntry = new ZipEntry(name);
                zos.putNextEntry(zipEntry);
                // Read file content and write to zip output stream
                FileInputStream fis = new FileInputStream(path);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                // Close zip entry and files input stream
                zos.closeEntry();
                fis.close();
            }

            // Close zip output stream and file output stream
            zos.close();
            fos.close();
        } catch (Exception e) { // IOException
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> getPathList(File dir) {
        List<String> pathList = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile()) {
                    pathList.add(file.getAbsolutePath());
                }
                // ignore sub dir
            }
        }
        return pathList;
    }
}
