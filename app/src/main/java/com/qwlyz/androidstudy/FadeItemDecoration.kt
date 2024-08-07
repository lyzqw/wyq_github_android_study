package com.qwlyz.androidstudy

import android.graphics.Canvas
import android.util.Log
import androidx.recyclerview.widget.RecyclerView

const val TAG = "FadeItemDecoration"

class FadeItemDecoration(private val fadeHeight: Int) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val childCount = parent.childCount
        val height = parent.height
        val fadeHeight = fadeHeight.coerceAtMost(height / 2)
        Log.d(TAG, "onDrawOver.childCount: $childCount")

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val childTop = child.top
            Log.d(TAG, "onDrawOver.childTop: $childTop")
            val alpha = when {
                childTop < fadeHeight -> 1f
                childTop > height - fadeHeight -> 0f
                else -> (height - childTop) / fadeHeight.toFloat()
            }
            child.alpha = alpha
        }
    }
}
