package com.qwlyz.androidstudy.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton

/**
 *
 * @author lyz
 */
class StrongButton  : Button {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int?) : super(context, attrs, defStyleAttr?:0)


    override fun onFinishInflate() {
        super.onFinishInflate()
    }

}