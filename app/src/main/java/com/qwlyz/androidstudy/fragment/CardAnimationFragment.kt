package com.qwlyz.androidstudy.fragment

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.Log
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SizeUtils
import com.blankj.utilcode.util.StringUtils
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.databinding.FragmentCardBinding
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
class CardAnimationFragment : BaseFragment() {

    private val binding by viewBinding(FragmentCardBinding::bind)

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://wanandroid.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun getLayoutId(): Int = R.layout.fragment_card

    override fun initData() {
        binding.start.setOnClickListener {
            binding.imageTarget.scaleX = 0f
            binding.imageTarget.scaleY = 0f
            binding.imageTarget.rotation = 0f

            val d = (2.5 * 1000L).toLong()
            // 创建缩放动画
            val scaleXAnimator = ObjectAnimator.ofFloat(binding.imageTarget, "scaleX", 1f)
            val scaleYAnimator = ObjectAnimator.ofFloat(binding.imageTarget, "scaleY", 1f)

            // 创建旋转动画
            val rotationAnimator = ObjectAnimator.ofFloat(binding.imageTarget, "rotation", 720f) // 720度，即两圈

            // 动画时长
            scaleXAnimator.duration = d
            scaleYAnimator.duration = d
            rotationAnimator.duration = d

            // 设置插值器为 DecelerateInterpolator，实现速率先快后慢
            val interpolator = AccelerateInterpolator()
//            scaleXAnimator.interpolator = interpolator
//            scaleYAnimator.interpolator = interpolator
//            rotationAnimator.interpolator = interpolator

            // 创建 AnimatorSet 来组合动画
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(scaleXAnimator, scaleYAnimator, rotationAnimator)
            // 启动动画
            animatorSet.start()
        }

    }
}

