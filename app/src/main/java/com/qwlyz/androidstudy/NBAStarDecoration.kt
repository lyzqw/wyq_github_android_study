package com.qwlyz.androidstudy

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SizeUtils
import com.qwlyz.androidstudy.fragment.StickFragment
import com.qwlyz.androidstudy.fragment.StickFragment.NBAStarAdapter


class NBAStar(val name: String, val groupName: String)
class NBAStarDecoration : RecyclerView.ItemDecoration() {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ColorUtils.getColor(R.color.black)
    }
    val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ColorUtils.getColor(R.color.purple_200)
    }
    val height = SizeUtils.dp2px(50f)

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)
        val adapter = parent.adapter
        if (adapter is NBAStarAdapter) {
            val childCount = parent.childCount
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight
            for (i in 0 until childCount) {
                val view = parent.getChildAt(i)
                val position = parent.getChildLayoutPosition(view)
                val groupHeader = adapter.isGroupHeader(position)
                if (groupHeader) {
                    canvas.drawRect(Rect(left, view.top - SizeUtils.dp2px(50f), right, view.top), paint)
                }
            }
        }
    }


    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val adapter = parent.adapter
        if (adapter is StickFragment.NBAStarAdapter) {
            val layoutManager = parent.layoutManager as LinearLayoutManager
            val position = layoutManager.findFirstVisibleItemPosition()
            val view = parent.findViewHolderForLayoutPosition(position)?.itemView ?: return
            val left = parent.paddingLeft.toFloat()
            val right = (parent.width - parent.paddingRight).toFloat()
            val top = parent.paddingTop.toFloat();
            val textRect = Rect()
            val groupHeader = adapter.isGroupHeader(position+1)
            if (adapter.getGroupName(position).isNullOrEmpty()) {
                return
            }

            if (groupHeader) {
//                item的高度 比 头部小
                val bottom = Math.min(SizeUtils.dp2px(50f), view.bottom)
//                画矩形
                canvas.drawRect(left, top, right, bottom + top, paint)
                val groupName = adapter.getGroupName(position)
                textPaint.getTextBounds(groupName, 0, groupName.length, textRect)
                canvas.drawText(
                    groupName,
                    left + 20,
                    top - SizeUtils.dp2px(50f) / 2 + textRect.height() / 2,
                    textPaint
                )

            } else {
                canvas.drawRect(left, top, right, top + SizeUtils.dp2px(50f), paint)
                val groupName = adapter.getGroupName(position)
                textPaint.getTextBounds(groupName, 0, groupName.length, textRect)
                canvas.drawText(
                    groupName, left + 20, top + height / 2 + textRect.height() / 2,
                    textPaint
                )
            }

        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val adapter = parent.adapter as StickFragment.NBAStarAdapter

        val position = parent.getChildLayoutPosition(view)

        if (adapter.isGroupHeader(position)) {
            outRect.set(0, height, 0, 0)
        } else {
            outRect.set(0, 1, 0, 0)
        }

    }
}


