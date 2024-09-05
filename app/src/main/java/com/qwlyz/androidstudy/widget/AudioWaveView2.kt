package com.qwlyz.androidstudy.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.Interpolator
import com.blankj.utilcode.util.SizeUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Random
import kotlin.math.max

class AudioWaveView2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var factor: Float = 1.0f
    private var va: ValueAnimator? = null
    var ratios: FloatArray = floatArrayOf(
        0.2f,
        0.3f,
        0.4f,
        0.5f,
        0.6f,
        0.7f,
        0.5f,
        0.3f,
        0.5f,
        0.8f,
        1.0f,
//        0.8f,
//        0.5f,
//        0.3f,
//        0.5f,
//        0.7f,
//        0.6f,
//        0.5f,
//        0.4f,
//        0.3f,
//        0.2f
    )

    val normalBg = "#FF1E1E"
    val cancelBg = "#BCBCBC"

    val linePaint by lazy {
        Paint().apply {
            color = Color.parseColor("#FFFFFF")
            style = Paint.Style.FILL
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    }
    val rlinePaint by lazy {
        Paint().apply {
            color = Color.parseColor("#FFE32F")
            style = Paint.Style.FILL
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    }

    val bgPaint by lazy {
        Paint().apply {
            color = Color.parseColor(normalBg)
            style = Paint.Style.FILL
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate((width / 2).toFloat(), (height / 2).toFloat())
        val distance = SizeUtils.dp2px(12f).toFloat()
        drawLine(-distance, canvas)
        drawLine(distance, canvas)
    }

    private fun drawLine(translateDx: Float, canvas: Canvas) {
        canvas.save()
        ratios.reversed().forEach {
            // Calculate dynamic top and bottom based on `it` and `factor`
            val top = interpolate(it * factor, 0f, 1f, -5f, -24f)
            val bottom = interpolate(it * factor, 0f, 1f, 5f, 24f)

            // Draw the rounded rectangle with the calculated top and bottom
            val left = -5f
            val right = 5f
            val rx = 5f
            val ry = 5f
            canvas.drawRoundRect(left, top, right, bottom, rx, ry, linePaint)

            canvas.translate(translateDx, 0f) // Move canvas for next rectangle
        }
        canvas.restore()
    }

    // Helper function to interpolate between two values
    private fun interpolate(
        input: Float,
        inMin: Float,
        inMax: Float,
        outMin: Float,
        outMax: Float
    ): Float {
        return outMin + (input - inMin) * (outMax - outMin) / (inMax - inMin)
    }

    var waveJob: Job? = null
    var started = false

    fun start() {
        if (started){
            return
        }
        waveJob?.cancel()
        started = true
        val random = Random()
        waveJob = GlobalScope.launch {
            while (started) {
                factor = random.nextFloat()
                invalidate()
                delay(200)
            }
        }
    }

    fun stop() {
        started = false
        waveJob?.cancel()
    }
}