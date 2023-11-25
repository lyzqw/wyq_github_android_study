package com.qwlyz.androidstudy.fragment

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.ogaclejapan.smarttablayout.utils.ViewPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.ViewPagerItems
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.databinding.FragmentHorizontalViewPagerBinding
import com.yuwq.libs_common.viewBinding


/**
 *
 * @author lyz
 */
class HorizontalScrollFragment : BaseFragment() {

    private val binding by viewBinding(FragmentHorizontalViewPagerBinding::bind)

    override fun getLayoutId(): Int = R.layout.fragment_horizontal_view_pager

    override fun initData() {

//        val adapter = FragmentPagerItemAdapter(
//            fragmentManager, FragmentPagerItems.with(context)
//                .add("直播", FlexBoxFragment::class.java)
//                .add("语音", IndicatorSeekBarFragment::class.java)
//                .add("聚会", IndicatorSeekBarFragment::class.java)
//                .create()
//        )

        val adapter = ViewPagerItemAdapter(
            ViewPagerItems.with(context)
                .add("", R.layout.custom_tab)
                .add("title", R.layout.custom_tab)
                .create()
        )


        binding.viewPager.adapter = adapter
        binding.tabLayout.setCustomTabView { container, position, adapter ->
            val rootView = View.inflate(context, R.layout.custom_tab, null)
            rootView.findViewById<TextView>(R.id.custom_text).text = if(position==0) "直播" else "语音"
            return@setCustomTabView rootView
        }
        binding.tabLayout.setViewPager(binding.viewPager)
    }

    inner class TabButtonAdapter : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun getCount(): Int = 3

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        }

        override fun destroyItem(container: View, position: Int, `object`: Any) {
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val rootView = View.inflate(container.context, R.layout.item_horizontal, null)
            container.addView(rootView)
            return rootView
        }
    }

//    private fun horizontal() {
//        hs.isFillViewport = true
//        hs.clipChildren = true
//        live.setOnClickListener {
//            //            val value: Int = ScreenUtils.getScreenWidth() / 2 - it.width
//            //            layout_tab.animate().translationX(value.toFloat()).start()
//            hs.scrollTo(300, 0)
//        }
//        voice.setOnClickListener {
//            hs.scrollTo(0, 0)
//            layout_tab.animate().translationX(0f).start()
//        }
//        party.setOnClickListener { }
//    }
}