package com.qwlyz.androidstudy

import android.graphics.Canvas
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class HalfTransparentItemDecoration : RecyclerView.ItemDecoration() {

    private val paint = Paint().apply {
        alpha = 127  // Set transparency
        style = Paint.Style.FILL
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val parentHeight = parent.height
        val halfHeight = parentHeight / 2

        // Draw transparent overlay
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val top = child.top
            val bottom = child.bottom

            if (bottom <= halfHeight) {
                // Draw transparent overlay on the top half
                c.drawRect(0f, top.toFloat(), parent.width.toFloat(), bottom.toFloat(), paint)
            } else if (top < halfHeight) {
                // Draw partial transparent overlay
                val height = halfHeight - top
                c.drawRect(0f, top.toFloat(), parent.width.toFloat(), (top + height).toFloat(), paint)
            }
        }
    }
}
