package com.qwlyz.androidstudy

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class TransparentItemDecoration(private val transparencyThreshold: Float) :
    RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val itemCount = parent.childCount
        val height = parent.height

        for (i in 0 until itemCount) {
            val child = parent.getChildAt(i) ?: continue
            val childTop = child.top
            val childBottom = child.bottom

            // Calculate the transparency based on the position of the item
            val transparency = when {
                childTop < height / 2 -> {
                    val fraction = (height / 2 - childTop).toFloat() / (height / 2)
                    fraction * transparencyThreshold
                }

                else -> transparencyThreshold
            }

            c.drawRect(
                child.left.toFloat(),
                childTop.toFloat(),
                child.right.toFloat(),
                childBottom.toFloat(),
                Paint().apply {
                    color = Color.argb((255 * transparency).toInt(), 0, 0, 0)
                    style = Paint.Style.FILL
                }
            )
        }
    }
}
