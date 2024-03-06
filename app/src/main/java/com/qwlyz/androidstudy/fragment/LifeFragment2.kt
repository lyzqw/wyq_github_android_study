package com.qwlyz.androidstudy.fragment

import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.databinding.FragmentLife1Binding
import com.qwlyz.androidstudy.databinding.FragmentLife2Binding
import com.qwlyz.androidstudy.databinding.FragmentPermissionDialogBinding
import com.yuwq.libs_common.viewBinding

class LifeFragment2 : BaseFragment() {

    private val binding by viewBinding(FragmentLife2Binding::bind)

    override fun getLayoutId(): Int  = R.layout.fragment_life2


    override fun initData() {

    }
}