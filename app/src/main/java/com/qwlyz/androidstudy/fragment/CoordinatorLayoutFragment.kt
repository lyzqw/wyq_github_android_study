package com.qwlyz.androidstudy.fragment

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.BottomFadeItemDecoration
import com.qwlyz.androidstudy.FadeFrameLayout
import com.qwlyz.androidstudy.FadeOutItemDecoration
import com.qwlyz.androidstudy.HalfTransparentItemDecoration
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.TransparentItemDecoration
import com.qwlyz.androidstudy.databinding.FragmentCoordlayoutBinding
import com.yuwq.libs_common.viewBinding

/**
 *
 * @author lyz
 */
class CoordinatorLayoutFragment : BaseFragment() {

    private val binding by viewBinding(FragmentCoordlayoutBinding::bind)

    override fun getLayoutId(): Int = R.layout.fragment_coordlayout

    override fun initData() {
        binding.btnEx.setOnClickListener {}
        binding.btnExpand.setOnClickListener {
            binding.rv.setFadingEdgeLength(SizeUtils.dp2px(300f))
        }
        val data = arrayListOf<String>()
        for (i in 0..100) {
            data.add(i.toString())
        }
//        binding.rv.addItemDecoration(BottomFadeItemDecoration(100))
        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.rv.adapter = object :
            BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_horizontal_tab, data) {
            override fun convert(holder: BaseViewHolder, item: String) {
                holder.setText(R.id.recommend, item)
            }

        }
        binding.rv.setFadingEdgeLength(ScreenUtils.getScreenHeight() / 2)
//        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
//                val totalItemCount = layoutManager.itemCount
//                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
//
//                // 计算屏幕中点的位置
//                val screenHeight = recyclerView.height
//                val midPoint = screenHeight / 2
//                val fadeStartPosition = midPoint + recyclerView.computeVerticalScrollOffset()
//
//                for (i in 0 until recyclerView.childCount) {
//                    val child = recyclerView.getChildAt(i)
//                    val position = layoutManager.getPosition(child)
//                    val childTop = child.top + recyclerView.computeVerticalScrollOffset()
//
//                    val alpha = when {
//                        childTop < fadeStartPosition -> 1f
//                        else -> 1f - ((childTop - fadeStartPosition).toFloat() / midPoint)
//                    }
//                    child.alpha = alpha.coerceIn(0f, 1f)
//                }
//            }
//        })


        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    show(recyclerView)
                    return
                }

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                for (i in firstVisibleItemPosition..lastVisibleItemPosition) {
                    val view = layoutManager.findViewByPosition(i) as? FadeFrameLayout
                    if (view != null) {
                        val top = view.top
                        val bottom = view.bottom

                        // Adjust visibility based on position or scrolling
                        val fadeThreshold = ScreenUtils.getScreenHeight() / 2
                        Log.d(TAG, "onScrolled: $top")
                        Log.d(TAG, "fadeThreshold: $fadeThreshold")
                        view.setFadeEnabled(false)
                        if (top < fadeThreshold) {
                            view.alpha = 0f
                        } else {
                            val lastView =
                                layoutManager.findViewByPosition(i - 1) as? FadeFrameLayout
                            if (lastView != null && can.not()) {
                                can = true
                                lastView.alpha = 1f
                                lastView.setFadeEnabled(true)
                            } else {
                                lastView?.setFadeEnabled(false)
                                view.alpha = 1.0f
                            }
                        }
                    }
                }
            }
        })

    }

    private fun show(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

        for (i in firstVisibleItemPosition..lastVisibleItemPosition) {
            val view = layoutManager.findViewByPosition(i) as? FadeFrameLayout
            if (view != null) {
                view.alpha = 1f
                view.setFadeEnabled(false)
            }
        }
    }

    var can = false
}