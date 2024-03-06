package com.qwlyz.androidstudy.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.TViewModel
import com.qwlyz.androidstudy.databinding.FragmentViewModelBinding
import com.qwlyz.androidstudy.lazy.v_user
import com.yuwq.libs_common.viewBinding

/**
 *
 * @author lyz
 */
class ViewModelFragment : BaseFragment() {

    private val binding by viewBinding(FragmentViewModelBinding::bind)

    private val taskViewModel by viewModels<TViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cache = savedInstanceState?.getInt("key")
        Log.d("life", "onCreate: $cache")
    }

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

//        taskViewModel.testLoad("1")

        taskViewModel.dataLiveData.observe(this){
            Log.d("wqq", "收到了: $it")
        }


//        taskViewModel.dataLiveData.observeForever{
//            Log.d("wqq", "收到了: $it")
//
//            taskViewModel.dataLiveData.removeObservers(this)
//        }

//        Log.d(TAG, "initData: ${taskViewModel.hashCode()}")
//        val t = ViewModelProvider(this).get(TViewModel::class.java)
//        val t2 = ViewModelProvider(this).get(TViewModel::class.java)
//        Log.d(TAG, "initData: ${taskViewModel.hashCode()}")
//        Log.d(TAG, "initData.t: ${taskViewModel.hashCode()}")
//        Log.d(TAG, "initData.t2: ${taskViewModel.hashCode()}")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("key",5)
        Log.d("life", "onSaveInstanceState.save")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("life", "onDestroyView: ")
    }
}