package com.qwlyz.androidstudy.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.View

/**
 * 音频按住波浪动画
 */
class AudioAnimation @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var viewHeight: Int = 0
    private var viewWidth: Int = 0
    private var factor: Float = 1.0f
    private var va: ValueAnimator? = null
    private val outPaint: Paint
    private val innerPaint: Paint

    init {
        innerPaint = Paint(ANTI_ALIAS_FLAG).also { it.color = Color.parseColor("#FFBC0F") }
        outPaint = Paint(ANTI_ALIAS_FLAG).also { it.color = Color.parseColor("#1AFFBC0F") }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = dp2px(108f).toInt()
        viewHeight = dp2px(108f).toInt()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val zero = 0f
        canvas.translate((viewWidth / 2).toFloat(), (viewHeight / 2).toFloat())
        //大圆
        val offset = 10 * factor
        val dipValue = 44f + offset
        canvas.drawCircle(zero, zero, dp2px(dipValue), outPaint)
        //画内圆
        canvas.drawCircle(zero, zero, dp2px(44f), innerPaint)
    }


    fun start() {
        if (va?.isStarted == true){
            va?.cancel()
        }
        va = ValueAnimator.ofFloat(0f, 0.2f, 0.6f, 1f).apply {
            addUpdateListener {
                factor = it.animatedValue as Float
                invalidate()
            }
            duration = 1000
            repeatCount = 9999
            start()
        }
    }

    fun stop() {
        va?.cancel()
        clearAnimation()
        factor = 1f
        invalidate()
    }

    fun dp2px(dipValue: Float): Float {
        val scale = context.applicationContext.resources.displayMetrics.scaledDensity
        return (dipValue * scale + 0.5f)
    }
}