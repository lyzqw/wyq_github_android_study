package com.qwlyz.androidstudy

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Shader
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BottomFadeItemDecoration(private val fadeHeight: Int) : RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        val shader = LinearGradient(
            0f, 0f, 0f, fadeHeight.toFloat(),
            Color.TRANSPARENT, Color.BLACK, Shader.TileMode.CLAMP
        )
        paint.shader = shader
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val width = parent.width
        val height = parent.height
        c.drawRect(0f, (height - fadeHeight).toFloat(), width.toFloat(), height.toFloat(), paint)
    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)


    }


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)


    }
}
