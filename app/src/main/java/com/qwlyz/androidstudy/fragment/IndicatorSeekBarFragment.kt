//package com.qwlyz.androidstudy.fragment
//
//import android.widget.SeekBar
//import com.qwlyz.androidstudy.BaseFragment
//import com.qwlyz.androidstudy.R
//import kotlinx.android.synthetic.main.fragment_indicator.*
//
///**
// *
// * @author lyz
// */
//class IndicatorSeekBarFragment : BaseFragment() {
//
//    override fun getLayoutId(): Int = R.layout.fragment_indicator
//
//    override fun initData() {
//        seek_bar.max = 600
//        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                indicator_view.setProgress(progress)
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//
//            }
//
//        })
//    }
//
//
//}