//package com.qwlyz.androidstudy.fragment
//
//import android.graphics.Color
//import android.graphics.drawable.Drawable
//import android.graphics.drawable.GradientDrawable
//import android.view.View
//import android.view.ViewGroup
//import android.widget.LinearLayout.HORIZONTAL
//import androidx.recyclerview.widget.StaggeredGridLayoutManager
//import com.chad.library.adapter.base.BaseQuickAdapter
//import com.chad.library.adapter.base.viewholder.BaseViewHolder
//import com.google.android.flexbox.*
//import com.qwlyz.androidstudy.BaseFragment
//import com.qwlyz.androidstudy.R
//import com.qwlyz.androidstudy.ext.getRandomColor
//import kotlinx.android.synthetic.main.fragment_flex.*
//
//
///**
// *
// * @author lyz
// */
//class FlexBoxFragment : BaseFragment() {
//
//    override fun getLayoutId(): Int = R.layout.fragment_flex
//
//
//    override fun initView() {
//        setRecyclerView()
//    }
//
//    private fun setRecyclerView() {
////        val layoutManager = FlexboxLayoutManager(context)
////        layoutManager.flexDirection = FlexDirection.COLUMN
////        layoutManager.flexWrap = FlexWrap.WRAP
////        layoutManager.justifyContent = JustifyContent.FLEX_END
//
//        recycler_view.layoutManager = StaggeredGridLayoutManager(3, HORIZONTAL)
//
//        val list_data = ArrayList<String>()
//        for (i in 0..2) {
//            list_data.add("71这里");
//            list_data.add("2222是设置");
//            list_data.add("的子元素");
//            list_data.add("的属性");
//            list_data.add("设置的");
//            list_data.add("属性可以参考");
//            list_data.add("上面的介绍。效");
//            list_data.add("果图如");
//            list_data.add("下");
//            list_data.add("2轻轻松松");
//        }
//
//        recycler_view.adapter =
//            object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_flex, list_data) {
//                override fun convert(holder: BaseViewHolder, item: String) {
//                    holder.setGone(R.id.view_line,holder.layoutPosition != 1)
//                    holder.setText(R.id.text, item)
//                    holder.getView<View>(R.id.text).background = getDrawable(getRandomColor())
//
//                }
//            }
//    }
//
//    fun getDrawable(c: Int): Drawable {
//        val drawable = GradientDrawable()
//        drawable.setColor(c)
//        drawable.cornerRadius = 20f
//        return drawable
//    }
//
//    override fun initData() {
//
//    }
//}