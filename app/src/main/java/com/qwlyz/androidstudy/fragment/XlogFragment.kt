//package com.qwlyz.androidstudy.fragment
//
//import com.android36kr.app.module.common.log.KrLog
//import com.qwlyz.androidstudy.BaseFragment
//import com.qwlyz.androidstudy.R
//import kotlinx.android.synthetic.main.fragment_x_log.*
//
///**
// *
// * @author lyz
// */
//class XlogFragment : BaseFragment() {
//
//    override fun getLayoutId(): Int  = R.layout.fragment_x_log
//
//    override fun initData() {
//
//        text.setOnClickListener {
//            KrLog.logDebugInfo("==============start===========")
//        }
//
//        upload.setOnClickListener {
//            KrLog.sendEmail(activity,null)
//        }
//    }
//
//}