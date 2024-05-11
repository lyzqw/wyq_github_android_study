package com.qwlyz.androidstudy

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.blankj.utilcode.util.SizeUtils

class DeleteView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mWidth: Float = 0f
    private var mHeight: Float = 0f
    private var crossSize = SizeUtils.dp2px(10f).toFloat()
    private var roundSize = SizeUtils.dp2px(8f).toFloat()
    private var coreSize = SizeUtils.dp2px(20f).toFloat()
    private val mPath = Path()

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
    }

    private val crossPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = SizeUtils.dp2px(1f).toFloat()
    }

    private val corePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = SizeUtils.dp2px(1f).toFloat()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat()
        mHeight = h.toFloat()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 绘制圆角矩形背景
        val zero = 0f
        canvas.drawRoundRect(
            zero,
            zero,
            width.toFloat(),
            height.toFloat(),
            roundSize,
            roundSize,
            backgroundPaint
        )
        drawCross(canvas)


        // 绘制三角形和正方形
        canvas.save()
        val translateX = (mWidth - coreSize) / 2
        val translateY = (mHeight - coreSize) / 2f

        canvas.translate(translateX, translateY)
        mPath.reset()
        val path = mPath.apply {
            lineTo(coreSize, zero)
            lineTo(coreSize, coreSize)
            lineTo(zero, coreSize)
            lineTo(-coreSize/2, coreSize/2)

//            moveTo(centerX, centerY - 2 * halfCrossSize)
//            lineTo(centerX - 2 * halfCrossSize, centerY)
//            lineTo(centerX, centerY + 2 * halfCrossSize)
//            lineTo(centerX + 2 * halfCrossSize, centerY)
            close()
        }
        canvas.drawPath(path, corePaint)
        canvas.restore()
    }

    private fun drawCross(canvas: Canvas) {
        canvas.save()
        canvas.rotate(45f, mWidth / 2, mHeight / 2); // 将旋转中心点移到画布的中心
        canvas.drawLine(
            mWidth / 2 - crossSize / 2,
            mHeight / 2,
            mWidth / 2 + crossSize / 2,
            mHeight / 2,
            crossPaint
        )
        canvas.drawLine(
            mWidth / 2,
            mHeight / 2 - crossSize / 2,
            mWidth / 2,
            mHeight / 2 + crossSize / 2,
            crossPaint
        )
        canvas.restore()
    }
}