package com.qwlyz.androidstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android36kr.app.module.common.log.KrLog

/**
 *
 * @author lyz
 */
abstract class BaseFragment : Fragment() {

    var TAG = javaClass.simpleName.toString()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        KrLog.logAction(javaClass.simpleName, "CV")
        return inflater.inflate(getLayoutId(), null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    override fun onResume() {
        super.onResume()
        KrLog.logAction(javaClass.simpleName, "R")
    }

    override fun onPause() {
        super.onPause()
        KrLog.logAction(javaClass.simpleName, "P")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        KrLog.logAction(javaClass.simpleName, "DV")
    }

    open fun initView(){}

    abstract fun getLayoutId(): Int

    abstract fun initData()
}