package com.qwlyz.androidstudy.fragment

import android.animation.ValueAnimator
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.PathUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.KUtils
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.databinding.FragmentXLogBinding
import com.tencent.mars.xlog.Log
import com.yuwq.libs_common.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File


/**
 *
 * @author lyz
 */
class XlogFragment : BaseFragment() {

    private val url = "https://picsum.photos/id/866/4704/3136"
    val url2 = "https://picsum.photos/id/237/200/300"

    private val binding by viewBinding(FragmentXLogBinding::bind)

    override fun getLayoutId(): Int = R.layout.fragment_x_log


    override fun initData() {
        lifecycleScope.launch(Dispatchers.Main){
            binding.btnWxVoice2.startA()
        }
        lifecycleScope.launch(Dispatchers.IO){
            binding.btnWxVoice.addVoiceSize(50)
        }
        binding.apply {
//            btnWxVoice.addVoiceSize(60)
//            btnWxVoice2.addVoiceSize(50)
            text.setOnClickListener {
//                btnWxVoice2.setCancel(true)
//                //va()
//
//                //startInfiniteAnimation()
//
////                .into(new CustomTarget<Bitmap>() {
////                @Override
////                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
////                    imageView.setImage(ImageSource.bitmap(resource));
////                }
////
////                @Override
////                public void onLoadCleared(@Nullable Drawable placeholder) {
////                    // 可以在这里处理清除操作
////                }
////            });
//                binding.imageSsi.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_INSIDE);
//
////                binding.imageSsi.animateScale(binding.imageSsi.maxScale);
////                binding.imageSsi.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP)
//                Glide.with(requireActivity())
//                    .asFile()
//                    .load(url2)
//                    .into(object : CustomTarget<File>() {
//                        override fun onResourceReady(resource: File, p1: Transition<in File>?) {
//                            android.util.Log.d(TAG, "onResourceReady: $resource")
//                            binding.imageSsi.setImage(ImageSource.uri(Uri.fromFile(resource)));
//                            binding.imageSsi.post {
//                                binding.imageSsi.setScaleAndCenter(binding.imageSsi.maxScale, null);
//                            }
//                        }
//
//                        override fun onLoadCleared(p0: Drawable?) {
//                            android.util.Log.d(TAG, "onLoadCleared: $p0")
//                        }
//                    })

            }

            upload.setOnClickListener {
//                KrLog.sendEmail(activity, null)
                //sendEmail()
//                job?.cancel()
//
//                Glide.with(requireActivity())
//                    .load(url2)
//                    .into(binding.photoView)
            }

        }
    }

    // 创建一个Job对象来控制协程
    var job: Job? = null

    fun startInfiniteAnimation() {
        if (job != null && job!!.isActive) {
            return
        }
        val scoreText = arrayOf(".    ", ". .  ", ". . .", ". . . .", ". . . . .", ". . . . . .")
        val duration = 3000L
        val interval = duration / scoreText.size
        job = lifecycleScope.launch {
            while (isActive) { // 无限循环，直到协程被取消
                scoreText.forEachIndexed { index, _ ->
                    binding.textView.text = scoreText[index % scoreText.size]
                    delay(interval)
                }
            }
        }

        // 假设你有一个取消动画的逻辑
        // scope.cancel() // 取消动画
    }

    private fun va() {
        //                val scoreText = arrayOf(".    ", ". .  ", ". . .")
        val scoreText = arrayOf(".    ", ". .  ", ". . .", ". . . .", ". . . . .", ". . . . . .")
        var valueAnimator: ValueAnimator? = null
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofInt(0, 6).setDuration(3000)
            valueAnimator?.setRepeatCount(ValueAnimator.INFINITE)
            valueAnimator?.addUpdateListener { animation ->
                val i = animation.animatedValue as Int
                binding.textView.text = scoreText[i % scoreText.size]
            }
        }
        valueAnimator?.start()
        Log.i("xlog", "==============start===========")

        Log.appenderFlushSync(true)
    }


    fun sendEmail() {
//        val logPath = context?.getExternalFilesDir(null)?.path + "/xlog"
        val logPath = PathUtils.getInternalAppDataPath() + "/xlog"

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