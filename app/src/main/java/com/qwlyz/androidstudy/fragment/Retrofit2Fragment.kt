package com.qwlyz.androidstudy.fragment

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SizeUtils
import com.blankj.utilcode.util.StringUtils
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.databinding.FragmentHorizontalViewPagerBinding
import com.qwlyz.androidstudy.databinding.FragmentRetrofitBinding
import com.yuwq.libs_common.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 *
 * @author lyz
 */
class Retrofit2Fragment : BaseFragment() {

    private val binding by viewBinding(FragmentRetrofitBinding::bind)

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://wanandroid.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun getLayoutId(): Int = R.layout.fragment_retrofit

    override fun initData() {
        binding.start.setOnClickListener {
            retrofit.create(Service::class.java).list()
                .enqueue(object : Callback<WQQAny> {
                    override fun onFailure(call: Call<WQQAny>, t: Throwable) {
                        LogUtils.d("error: ${t.message}")
                    }

                    override fun onResponse(call: Call<WQQAny>, response: Response<WQQAny>) {
                        LogUtils.d("success: ${call.request().url()}")
                    }
                })
        }

        binding.load.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
//                val r = retrofit.create(Service::class.java).list2()
//                LogUtils.d("suspend 这个会crash: $r")

                val r = retrofit.create(Service::class.java).list().execute()
                LogUtils.d("suspend 这个没有问题: $r")

            }
        }

        val iconWidth = SizeUtils.dp2px(44f)
        QMUIGroupListView.newSection(activity)
            .setLeftIconSize(iconWidth, iconWidth)
            .addItemView(
                getCommonListItemView(
                    R.drawable.qmui_icon_checkmark,
                    R.string.app_name,
                    SizeUtils.dp2px(44f)
                ), {
                    Log.d("TAG", "initData: ")
            })
//            .setShowAccessory(true) // 显式设置显示右侧箭头
            .addTo(binding.gmList)


    }

    private fun getCommonListItemView(
        drawableId: Int,
        titleId: Int,
        itemHeight: Int,
        detailText: String? = null,
    ): QMUICommonListItemView {
        return binding.gmList.createItemView(
            ContextCompat.getDrawable(requireActivity(), drawableId),
            StringUtils.getString(titleId),
            detailText,
            QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON,
            itemHeight
        )
    }


    interface Service {

        @GET("/list")
        fun list(): Call<WQQAny>

        @GET("/list")
        suspend fun list2(): Call<WQQAny>
    }

    class WQQAny : Any()
}

