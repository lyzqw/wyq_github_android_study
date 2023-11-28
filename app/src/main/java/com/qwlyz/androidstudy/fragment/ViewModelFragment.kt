package com.qwlyz.androidstudy.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.TViewModel
import com.qwlyz.androidstudy.databinding.FragmentViewModelBinding
import com.yuwq.libs_common.viewBinding

/**
 *
 * @author lyz
 */
class ViewModelFragment : BaseFragment() {

    private val binding by viewBinding(FragmentViewModelBinding::bind)

    private val taskViewModel by viewModels<TViewModel>()
    override fun getLayoutId(): Int = R.layout.fragment_view_model

    override fun initData() {
        binding.showDialog.setOnClickListener {
            taskViewModel.testLoad("2")
        }
        binding.share.setOnClickListener {
            taskViewModel.dataLiveData.observe(this@ViewModelFragment){
                Log.d("wqq", "后注册收到了: $it")
            }
        }

        taskViewModel.testLoad("1")

//        taskViewModel.dataLiveData.observe(this){
//            Log.d("wqq", "收到了: $it")
//        }


        taskViewModel.dataLiveData.observeForever{
            Log.d("wqq", "收到了: $it")

            taskViewModel.dataLiveData.removeObservers(this)
        }


    }
}