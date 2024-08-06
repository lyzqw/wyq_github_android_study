package com.qwlyz.androidstudy

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Shader
import android.util.AttributeSet
import android.widget.FrameLayout

class FadeFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
    }
    private var fadeEnabled: Boolean = false // 控制渐隐效果的开关

    init {
        setWillNotDraw(false)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // 更新 shader 高度以匹配视图的高度
        val shader = LinearGradient(
            0f, 0f, 0f, h.toFloat(),
            Color.TRANSPARENT, Color.BLACK,
            Shader.TileMode.CLAMP
        )
        paint.shader = shader
        // 设置硬件加速以支持 Xfermode
        setLayerType(LAYER_TYPE_HARDWARE, paint)
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        if (fadeEnabled) {
            // 设置 xfermode 为 DST_IN 模式，保留目标图像的 alpha 通道
            val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
            paint.xfermode = xfermode

            // 绘制一个覆盖整个View的渐隐效果
            canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)

            // 重置 xfermode
            paint.xfermode = null
        }
    }

    // 方法来设置渐隐效果开关
    fun setFadeEnabled(enabled: Boolean) {
        fadeEnabled = enabled
        invalidate() // 重新绘制视图
    }
}
