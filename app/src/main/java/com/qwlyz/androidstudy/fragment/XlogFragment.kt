package com.qwlyz.androidstudy.fragment

import com.qwlyz.androidstudy.KUtils
import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.PathUtils
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.databinding.FragmentXLogBinding
import com.tencent.mars.xlog.Log
import com.yuwq.libs_common.viewBinding
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File
import java.lang.RuntimeException

/**
 *
 * @author lyz
 */
class XlogFragment : BaseFragment() {

    private val binding by viewBinding(FragmentXLogBinding::bind)

    override fun getLayoutId(): Int = R.layout.fragment_x_log


    override fun initData() {

        binding.apply {
            text.setOnClickListener {
//                KrLog.logDebugInfo("==============start===========")

                Log.i("xlog", "==============start===========")

                Log.appenderFlushSync(true)
            }

            upload.setOnClickListener {
//                KrLog.sendEmail(activity, null)
                sendEmail()
            }

        }
    }


    fun sendEmail() {
//        val logPath = context?.getExternalFilesDir(null)?.path + "/xlog"
        val logPath = PathUtils.getInternalAppDataPath()+"/xlog"

        android.util.Log.d(TAG, "sendEmail: $logPath")
        Observable.just(0)
            .map {
                Log.appenderFlushSync(true)
                val rootDir = context?.getExternalFilesDir(null)!!
                val zipFile = rootDir.absolutePath + File.separator + "Kr_Z.zip";
                val oldZipFile = File(zipFile);
                if (oldZipFile.exists()) {
                    oldZipFile.delete();
                }

                val logDir = File(logPath)
                if (logDir != null && logDir.exists()) {
                    // compress ...

                    KUtils.compressFiles(logDir.absolutePath, zipFile);
                    // return zip path
                    return@map zipFile;
                } else {
                    throw RuntimeException("111")
                }

            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                LogUtils.d("path: " + it);
                LogUtils.d("content: " + FileIOUtils.readFile2String(it));
            }
//                .map(integer -> {
//                    val  rootDir = context.getExternalFilesDir(null);
//                    if (rootDir == null) {
//                        // get root dir failure
//                        return "";
//                    }
//
//                    // flush log
//                    Log.appenderFlush(true);
//
//                    String zipFile = rootDir + File.separator + LOG_ZIP_FILENAME;
//                    File oldZipFile = new File(zipFile);
//                    if (oldZipFile.exists()) {
//                        //noinspection ResultOfMethodCallIgnored
//                        oldZipFile.delete();
//                    }
//
//                    File logDir = context.getExternalFilesDir(KR_LOG_DIR);
//                    if (logDir != null && logDir.exists()) {
//                        // compress ...
//                        compressFiles(logDir.getAbsolutePath(), zipFile);
//                        // return zip path
//                        return zipFile;
//                    }
//                    return "";
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(path -> {
//
//                    LogUtils.d("path: "+path);
//                    LogUtils.d("content: "+FileIOUtils.readFile2String(path));
//
//
////                    Intent intent = new Intent(Intent.ACTION_SEND);
////                    String[] receivers = new String[] { EMAIL_RECEIVER };
////                    intent.putExtra(Intent.EXTRA_EMAIL, receivers);
////                    intent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.feedback_email_content));
////                    if (!TextUtils.isEmpty(path)) {
////                        intent.setType("application/extension");
////                        intent.putExtra(Intent.EXTRA_STREAM, FileUtil.file2Uri(context,new File(path)));
////                    }
////                    context.startActivity(
////                            Intent.createChooser(intent, context.getString(R.string.feedback_email_chooser_text)));
////                    if (callback != null) {
////                        callback.finish();
////                    }
//                }, error -> {
//                    if (BuildConfig.DEBUG) {
//                        error.printStackTrace();
//                    }
//                    if (callback != null) {
//                        callback.finish();
//                    }
//                });
    }
}