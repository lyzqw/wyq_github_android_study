package com.qwlyz.androidstudy

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class FadeOutItemDecoration : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val childCount = parent.childCount
        val recyclerViewHeight = parent.height

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i) ?: continue
            val itemTop = child.top
            val itemBottom = child.bottom
            
            val itemHeight = itemBottom - itemTop
            val fadeHeight = itemHeight / 2
            
            // Determine the transparency
            val transparency: Float = when {
                itemTop < fadeHeight -> {
                    val visibleHeight = fadeHeight - itemTop
                    val fraction = visibleHeight.toFloat() / fadeHeight
                    1 - fraction // Transparent at the top, opaque at the bottom
                }
                else -> 1f // Fully opaque
            }

            // Apply the transparency
            val paint = Paint().apply {
                color = Color.TRANSPARENT
                style = Paint.Style.FILL
            }

            c.drawRect(
                child.left.toFloat(),
                itemTop.toFloat(),
                child.right.toFloat(),
                itemBottom.toFloat(),
                paint
            )
        }
    }
}
