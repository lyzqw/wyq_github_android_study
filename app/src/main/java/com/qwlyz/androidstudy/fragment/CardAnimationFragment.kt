package com.qwlyz.androidstudy.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.databinding.FragmentCardBinding
import com.yuwq.libs_common.viewBinding

/**
 *
 * @author lyz
 */
class CardAnimationFragment : BaseFragment() {

    private val binding by viewBinding(FragmentCardBinding::bind)
    private var isFrontViewVisible = true


    override fun getLayoutId(): Int = R.layout.fragment_card

    override fun initData() {
        binding.start.setOnClickListener {
            binding.imageTarget.scaleX = 0f
            binding.imageTarget.scaleY = 0f
            binding.imageTarget.rotation = 0f
            val d = (2.5 * 1000L).toLong()
            binding.imageTarget.animate().scaleY(1f).scaleX(1f).rotation(720f).setDuration(d)
                .start()
        }

        binding.mainFlContainer.setOnClickListener {
            flipCard()
        }
        initAnimation()
        setCameraDistance(); // 设置镜头距离
    }


    private fun setCameraDistance() {
        var distance = 16000;
        var scale = getResources().getDisplayMetrics().density * distance;
        binding.front.flFront.setCameraDistance(scale);
        binding.back.flBack.setCameraDistance(scale);
    }

    lateinit var inSet: AnimatorSet
    lateinit var outSet: AnimatorSet
    var back = false

    private fun flipCard() {

        if (back.not()) {
            inSet.setTarget(binding.back.flBack)
            outSet.setTarget(binding.front.flFront)
        } else {
            inSet.setTarget(binding.front.flFront)
            outSet.setTarget(binding.back.flBack)
        }

        back = back.not()

        inSet.start()
        outSet.start()
    }

    private fun initAnimation() {
        inSet = AnimatorSet()
        val animator1 = ObjectAnimator.ofFloat(null, "rotationY", -180f, 0f);
        var animator2 = ObjectAnimator.ofFloat(null, "alpha", 0.0f, 1f);
        animator2.setStartDelay(250);
        animator2.setDuration(0);
        animator1.duration = 500

        inSet.playTogether(animator1, animator2);


        outSet = AnimatorSet()
        val animator_ = ObjectAnimator.ofFloat(null, "rotationY", 0f, 180f);
        val animator2_ = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f);
        animator2_.setStartDelay(250)
        animator2_.setDuration(0);
        animator_.duration = 500

        outSet.playTogether(animator_, animator2_);

    }
}

