package com.qwlyz.androidstudy


import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class HalfTransparentItemDecoration : RecyclerView.ItemDecoration() {

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val childCount = parent.childCount
        val paint = Paint().apply {
            alpha = 128 // 设置半透明度
        }

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val itemHeight = child.height
            val halfHeight = itemHeight / 2

            // 画半透明的上半部分
            val transparentRect = Rect(
                child.left,
                child.top,
                child.right,
                child.top + halfHeight
            )
            paint.color = Color.TRANSPARENT
            c.drawRect(transparentRect, paint)

            // 画正常的下半部分
            val normalRect = Rect(
                child.left,
                child.top + halfHeight,
                child.right,
                child.bottom
            )
            paint.color = Color.BLACK // 设置颜色
            c.drawRect(normalRect, paint)
        }
    }
}
