package com.qwlyz.androidstudy.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.qwlyz.androidstudy.BaseFragment
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
        val tabLayout = binding.layoutTab
        mTitles.forEach {
            tabLayout.addTab(tabLayout.newTab().apply { text = it })
        }
        val data = arrayListOf<String>()
        for (i in 0..100) {
            data.add(i.toString())
        }
        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.rv.adapter = object :
            BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1, data) {
            override fun convert(holder: BaseViewHolder, item: String) {
                holder.setText(android.R.id.text1, item)
            }

        }
    }

}