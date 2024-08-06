package com.qwlyz.androidstudy

import androidx.recyclerview.widget.RecyclerView

class HalfTransparentLayoutManager : RecyclerView.LayoutManager() {




    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
        detachAndScrapAttachedViews(recycler)

        var offsetY = 0
        for (i in 0 until itemCount) {
            val view = recycler.getViewForPosition(i)
            addView(view)
            measureChildWithMargins(view, 0, 0)
            val width = getDecoratedMeasuredWidth(view)
            val height = getDecoratedMeasuredHeight(view) / 2 // 只显示一半高度

            layoutDecorated(view, 0, offsetY, width, offsetY + height)
            offsetY += height

            view.alpha = 0.5f // 设置半透明
        }
    }

    override fun canScrollVertically(): Boolean {
        return true
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler, state: RecyclerView.State?): Int {
        offsetChildrenVertical(-dy)
        return dy
    }
}
