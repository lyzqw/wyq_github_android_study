package com.qwlyz.androidstudy.fragment

import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.TAG_LIFE
import com.qwlyz.androidstudy.TViewModel
import com.qwlyz.androidstudy.databinding.FragmentLife1Binding
import com.qwlyz.androidstudy.databinding.FragmentPermissionDialogBinding
import com.yuwq.libs_common.viewBinding

class LifeFragment1 : BaseFragment() {

    private val binding by viewBinding(FragmentLife1Binding::bind)
    private val taskViewModel by viewModels<TViewModel>()
    override fun getLayoutId(): Int  = R.layout.fragment_life1


    override fun initData() {
        Log.d(TAG_LIFE, "initData: fragment1")
        taskViewModel.dataLiveData.observe(this){
            Log.d(TAG_LIFE, "initData: fragment1 dataLiveData observe")
        }
    }
}