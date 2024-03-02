package com.qwlyz.androidstudy

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class HRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {


    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        if (e.action == MotionEvent.ACTION_DOWN) return false

        return super.onInterceptTouchEvent(e)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        Log.d("liuyuzhe", "onTouchEvent: "+e.getX())
        Log.d("liuyuzhe", "onTouchEvent: "+e.getY())
        return super.onTouchEvent(e)
    }
}