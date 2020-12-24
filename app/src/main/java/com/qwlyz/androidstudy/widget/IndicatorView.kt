package com.qwlyz.androidstudy.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SizeUtils

/**
 *
 * @author lyz
 */
class IndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var indicatorHeight = 23F //不包括角标
    var arrowWidth = 20F //角标高度

    var tipText = 60
    val textPaint = Paint()
    val rowPaint = Paint()
    val columPaint = Paint()
    val indicatorPaint = Paint()

    private val dp1 = SizeUtils.dp2px(1f).toFloat()

    init {
        textPaint.textSize = SizeUtils.dp2px(14f).toFloat()
        textPaint.color = ColorUtils.getColor(android.R.color.holo_green_light)
        rowPaint.color = ColorUtils.getColor(android.R.color.holo_red_light)
        columPaint.color = ColorUtils.getColor(android.R.color.holo_blue_light)

        indicatorPaint.isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = SizeUtils.dp2px(80f)
        val height = SizeUtils.dp2px(28f)
        Log.d(TAG, "onMeasure: " + width)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawLine(canvas)
        canvas.save()
        indicatorPaint.strokeWidth = dp1
        indicatorPaint.style = Paint.Style.STROKE
        indicatorPaint.color = ColorUtils.getColor(android.R.color.holo_purple)
        drawIndicator(canvas)
        canvas.restore()

        canvas.save()
        indicatorPaint.style = Paint.Style.FILL
        indicatorPaint.color = ColorUtils.getColor(android.R.color.black)
        drawIndicator(canvas)
        canvas.restore()

        drawText(canvas)
    }

    private fun drawIndicator(canvas: Canvas) {
        val path = Path()
        val x = dp1 //x起点
        path.moveTo(x, x)
        path.lineTo(width.toFloat() - x, x)
        path.lineTo(width.toFloat() - x, SizeUtils.dp2px(indicatorHeight).toFloat())

        val arrowX = (width - arrowWidth) / 2 + arrowWidth
        path.lineTo(arrowX, SizeUtils.dp2px(indicatorHeight).toFloat())
        path.lineTo(width / 2f, height.toFloat() - dp1)
        path.lineTo((width - arrowWidth) / 2, SizeUtils.dp2px(indicatorHeight).toFloat())
        path.lineTo(dp1, SizeUtils.dp2px(indicatorHeight).toFloat())
        path.close()
        canvas.drawPath(path, indicatorPaint)
    }

    private fun drawText(canvas: Canvas) {
        canvas.translate(width.toFloat() / 2, height.toFloat() / 2)
        val text = "$tipText km"
        val textWidth: Float = textPaint.measureText(text)
        val baseLineY: Float =
            Math.abs(textPaint.ascent() + textPaint.descent() + SizeUtils.dp2px(4f)) / 2
        canvas.drawText(text, -textWidth / 2, baseLineY, textPaint)
    }

    private fun drawLine(canvas: Canvas) {
        canvas.save()
        canvas.drawLine(
            0f,
            (height / 2).toFloat(),
            width.toFloat(),
            (height / 2).toFloat(),
            rowPaint
        )
        canvas.restore()
        canvas.save()
        canvas.drawLine(
            width.toFloat() / 2, 0f,
            width.toFloat() / 2,
            height.toFloat(),
            columPaint
        )
        canvas.restore()
    }


    fun setProgress(progress: Int) {
        tipText = progress
        invalidate()
    }

    companion object {
        val TAG = "wqq"
    }
}