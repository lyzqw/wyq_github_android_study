package com.qwlyz.androidstudy.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.SeekBar
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.databinding.FragmentHorizontalViewPagerBinding
import com.qwlyz.androidstudy.databinding.FragmentPopupwindowBinding
import com.yuwq.libs_common.viewBinding

/**
 *
 * @author lyz
 */
class PopupWindowFragment : BaseFragment() {

    private val binding by viewBinding(FragmentPopupwindowBinding::bind)

    lateinit var seekBarView: SeekBar
    lateinit var popView: View
    lateinit var target: View
    lateinit var mPopupWindow: PopupWindow

    override fun getLayoutId(): Int = R.layout.fragment_popupwindow

    override fun initData() {
        target = binding.tvHelloWorld
        val showView = binding.show


        showView.setOnClickListener {
            initPopWindow()
        }

        binding.update.setOnClickListener {
            Log.d("wqq", "onCreate: update..")
            popView.visibility = View.VISIBLE
//            target.offsetLeftAndRight(100)
//            mPopupWindow.update(target, mPopupWindow.width, mPopupWindow.height)
        }

        binding.dismiss.setOnClickListener {
            popView.visibility = View.GONE
        }


        seekBarView = binding.seekBar
        seekBarView.progress = 0
        seekBarView.max = 600
        seekBarView.setOnTouchListener { v, event ->

            return@setOnTouchListener false
        }
        seekBarView.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val percent = progress * 1.0 / 600
                val offset = (resources.displayMetrics.widthPixels * percent).toInt()

                val layoutParams = target.layoutParams as LinearLayout.LayoutParams
                layoutParams.leftMargin = offset
                target.layoutParams = layoutParams

                initPopWindow()
                popView.visibility = View.VISIBLE
                mPopupWindow.update(target, mPopupWindow.width, mPopupWindow.height)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                popView.visibility = View.GONE
            }

        })
    }

    private fun initPopWindow() {
        if (::mPopupWindow.isInitialized) return
        mPopupWindow = PopupWindow(context)

        popView = layoutInflater.inflate(R.layout.layout_popowindow, null)
        val layoutParams =
            ViewGroup.LayoutParams(
                resources.displayMetrics.widthPixels,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        popView.layoutParams = layoutParams
        mPopupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mPopupWindow.contentView = popView
        val height = -150
        popView.visibility = View.GONE
        mPopupWindow.showAsDropDown(target, 0, height)
    }
}