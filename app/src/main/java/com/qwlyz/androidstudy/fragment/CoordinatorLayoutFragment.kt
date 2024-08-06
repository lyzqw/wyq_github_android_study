package com.qwlyz.androidstudy.fragment

import android.util.Log
import android.widget.FrameLayout
import android.widget.PopupWindow
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ScreenUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.HalfTransparentItemDecoration
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.databinding.FragmentCoordlayoutBinding
import com.yuwq.libs_common.viewBinding

/**
 *
 * @author lyz
 */
class CoordinatorLayoutFragment : BaseFragment() {


//    ViewPager mViewPager;
//    List<Fragment> mFragments;
//    private val mTabLayout;

    val mTitles = arrayListOf<String>("主页", "微博", "相册");


    private val binding by viewBinding(FragmentCoordlayoutBinding::bind)

    override fun getLayoutId(): Int = R.layout.fragment_coordlayout

    override fun initData() {
//        binding.expandLayout.parallax = 0.5f
//        binding.expandLayout.updateLayoutParams<FrameLayout.LayoutParams> {
//            this.height = ScreenUtils.getScreenHeight() / 2
//        }
        binding.btnEx.setOnClickListener {
            binding.expandLayout.collapse(false)
        }
        binding.btnExpand.setOnClickListener {
            binding.expandLayout.expand(false)
//            binding.expandLayout.updateLayoutParams<FrameLayout.LayoutParams> {
//                this.height = ScreenUtils.getScreenHeight()
//            }
        }
//        val tabLayout = binding.layoutTab
//        mTitles.forEach {
//            tabLayout.addTab(tabLayout.newTab().apply { text = it })
//        }
        val data = arrayListOf<String>()
        for (i in 0..100) {
            data.add(i.toString())
        }

//        binding.rv.addItemDecoration(HalfTransparentItemDecoration())
        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {


            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.d(TAG, "onScrolled: $dy")
//                if (dy > 0) return
//                if (expand) {/*
//                    return
//                }
//                expand = true*/
                binding.expandLayout.expand(false)
//                binding.expandLayout.updateLayoutParams<FrameLayout.LayoutParams> {
//                    this.height = ScreenUtils.getScreenHeight()
//                }
            }
        })
        binding.rv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, true)
        binding.rv.adapter = object :
            BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_horizontal_tab, data) {
            override fun convert(holder: BaseViewHolder, item: String) {
                holder.setText(R.id.recommend, item)
            }

        }
    }

    private var expand: Boolean = false
}