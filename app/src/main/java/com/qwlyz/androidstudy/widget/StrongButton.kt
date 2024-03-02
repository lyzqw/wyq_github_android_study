package com.qwlyz.androidstudy.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton

/**
 *
 * @author lyz
 */
class StrongButton  : androidx.appcompat.widget.AppCompatButton {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int?) : super(context, attrs, defStyleAttr?:0)

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("liuyuzhe", "onTouchEvent:  true")
        parent.requestDisallowInterceptTouchEvent(true)
        return true
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        Log.d("liuyuzhe", "onFinishInflate: ")

        setOnClickListener {
            Log.d("liuyuzhe", "onFinishInflate: click")
        }
    }



}