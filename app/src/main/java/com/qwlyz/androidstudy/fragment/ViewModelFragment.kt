package com.qwlyz.androidstudy.fragment

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.graphics.Rect
import android.os.Bundle
import android.os.Looper
import android.os.Trace
import android.util.Base64
import android.util.Log
import android.view.TouchDelegate
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.qwlyz.androidstudy.AA
import com.qwlyz.androidstudy.BB
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.TViewModel
import com.qwlyz.androidstudy.databinding.FragmentViewModelBinding
import com.yuwq.libs_common.viewBinding
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


/**
 *
 * @author lyz
 */
class ViewModelFragment : BaseFragment() {

    companion object {
        const val section_name = "trace_ViewModelFragment"
    }

    private val binding by viewBinding(FragmentViewModelBinding::bind)

    private val taskViewModel by viewModels<TViewModel>()
    private val rvViewModel by lazy { taskViewModel }
    private val sdViewModel by lazy { taskViewModel }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cache = savedInstanceState?.getInt("key")
        Log.d("life", "onCreate: $cache")
    }

    override fun getLayoutId(): Int = R.layout.fragment_view_model

    override fun initData() {
        touchDelegate()

        binding.cal.setOnClickListener {
            Log.d(TAG, "算法")
//            Cal.main()
        }
        binding.send1.setOnClickListener {
            Log.d(TAG, "initData: send1")
            sdViewModel.send1.value = "1111"
        }
        binding.send2.setOnClickListener {
            Log.d(TAG, "initData: send2")
            sdViewModel.send2.value = "222"
        }
        binding.send3.setOnClickListener {
            //FrsWWvvY5tKYBgK4kyragXZ/xNA=
//            FrsWWvvY5tKYBgK4kyragXZ/xNA=
//            val sign = getSignatureHash(requireActivity())
//            Log.d(TAG, "sign: $sign")
            try {
                Trace.beginSection(section_name)
                val aa = AA(BB())
                aa.eat()
                Log.d(TAG, "initData: send33")
                sdViewModel.send1.value = "333"
            } finally {
                Trace.endSection()
            }

        }
        binding.receive.setOnClickListener {
            Log.d(TAG, "initData: receive")
            rvViewModel.receive.addSource(sdViewModel.send1, object : Observer<String> {

                override fun onChanged(t: String) {
                    Log.d(TAG, "onChanged: send1: $t")
                    rvViewModel.receive.value = t
                }
            })
            rvViewModel.receive.addSource(sdViewModel.send2, object : Observer<String> {

                override fun onChanged(t: String) {
                    Log.d(TAG, "onChanged: send2: $t")
                    rvViewModel.receive.value = t
                }
            })
            rvViewModel.receive.observe(this@ViewModelFragment, object : Observer<String> {
                override fun onChanged(t: String?) {
                    Log.d(TAG, "onChanged.receive: $t")
                }
            })
        }
//        binding.showDialog.setOnClickListener {
//            taskViewModel.testLoad("2")
//        }
//        binding.share.setOnClickListener {
//            taskViewModel.dataLiveData.observe(this@ViewModelFragment){
//                Log.d("wqq", "后注册收到了: $it")
//            }
//        }
//
////        taskViewModel.testLoad("1")
//
//        taskViewModel.dataLiveData.observe(this){
//            Log.d("wqq", "收到了: $it")
//        }


//        taskViewModel.dataLiveData.observeForever{
//            Log.d("wqq", "收到了: $it")
//
//            taskViewModel.dataLiveData.removeObservers(this)
//        }

//        Log.d(TAG, "initData: ${taskViewModel.hashCode()}")
//        val t = ViewModelProvider(this).get(TViewModel::class.java)
//        val t2 = ViewModelProvider(this).get(TViewModel::class.java)
//        Log.d(TAG, "initData: ${taskViewModel.hashCode()}")
//        Log.d(TAG, "initData.t: ${taskViewModel.hashCode()}")
//        Log.d(TAG, "initData.t2: ${taskViewModel.hashCode()}")

    }

    private fun touchDelegate() {
        // 假设binding是你的视图绑定对象，cal是你的CalendarView或者自定义视图
        binding.cal.post { // 获取cal视图的父视图
            val parentView = binding.cal.parent as View
            // 创建一个矩形，该矩形定义了你希望的触摸范围的边界
            val delegateArea = Rect()
            binding.cal.getHitRect(delegateArea)
            // 扩大触摸范围，比如向上扩大100像素，向下扩大100像素
            delegateArea.top -= 300
            delegateArea.bottom += 300
            // 创建一个TouchDelegate对象，并传入你定义好的矩形范围以及需要扩大触摸范围的View对象
            val touchDelegate = TouchDelegate(delegateArea, binding.cal)
            // 设置TouchDelegate对象给视图的父视图，从而扩大触摸范围
            parentView.touchDelegate = touchDelegate
        }
        binding.llCalParent.setOnClickListener {
            Log.d(TAG, "llCalParent.touchDelegate: ")
            Looper.getMainLooper().setMessageLogging {

            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("key", 5)
        Log.d("life", "onSaveInstanceState.save")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("life", "onDestroyView: ")
    }

    fun getSignatureHash(context: Context): String? {
        try {
            // 获取包管理器
            val pm: PackageManager = context.getPackageManager()
            // 获取应用程序信息
            val packageName: String = context.getPackageName()
            val flags = PackageManager.GET_SIGNATURES
            val packageInfo = pm.getPackageInfo(packageName, flags)
            // 获取签名数组
            val signatures: Array<Signature> = packageInfo.signatures

            // 获取第一个签名的哈希值
            val firstSignature: Signature = signatures[0]
            val signatureBytes: ByteArray = firstSignature.toByteArray()
            val md = MessageDigest.getInstance("SHA")
            val digest = md.digest(signatureBytes)
            return Base64.encodeToString(digest, Base64.NO_WRAP) // 将哈希值编码为Base64字符串
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return null
    }
}