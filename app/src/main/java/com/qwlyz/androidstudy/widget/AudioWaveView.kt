package com.qwlyz.androidstudy.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.Interpolator
import java.util.Random
import kotlin.math.max

class AudioWaveView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

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

    val bgPaint by lazy {
        Paint().apply {
            color = Color.parseColor(normalBg)
            style = Paint.Style.FILL
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    }
    private var showWidth = 0 //显示的宽度

    /**
     * 每条音频线能显示的最大值与showVoiceSize的比值
     */
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
        0.8f,
        0.5f,
        0.3f,
        0.5f,
        0.7f,
        0.6f,
        0.5f,
        0.4f,
        0.3f,
        0.2f
    )

    var lineHandler: LineHandler? = null
    private val widthRotas: Float = 0.5f //当前显示的宽度

    init {
        buildDrawLines()
        Thread {
            Looper.prepare()
            lineHandler = LineHandler(Looper.myLooper()!!)
            Looper.loop()
        }.start()

    }

    class DrawLine {
        var rectF: RectF? = null
        var maxSize: Int = 0
        var lineSize: Int = 0
        var small: Boolean = true //是否缩小模式，
        var rotas: Float = 1.0f
        var timeCompletion: Float = 0f //时间完成度，返回在0-1
        var duration: Int = 0
    }

    var MIN_VOICE_SIZE: Int = 20
    private val LINE_WIDTH = 10 //音量条的宽度
    lateinit var arrayList: ArrayList<DrawLine>

    fun buildDrawLines() {
        arrayList = ArrayList<DrawLine>()
        arrayList.clear()
        ratios.forEach {
            val maxSize = (MIN_VOICE_SIZE * it).toInt()+1
            val rect = RectF(
                (-LINE_WIDTH / 2).toFloat(),
                (-maxSize / 2).toFloat(),
                (LINE_WIDTH / 2).toFloat(),
                (maxSize / 2).toFloat()
            )
            val drawLine = DrawLine()
            drawLine.maxSize = maxSize.toInt()
            drawLine.rectF = rect
            drawLine.lineSize = Random().nextInt(maxSize)
            drawLine.rotas = it


            //通过设置不同的时间完成度，让每个音量条有不同的初始值。可以实现参差不齐的效果
            // drawLine.timeCompletion = ratio;
            drawLine.duration = (400 * (1.0f / it)).toInt()
            arrayList.add(drawLine)
        }

    }

    private val mInterpolator: Interpolator = BounceInterpolator()


    fun setCancel(cancel: Boolean) {
        if (cancel) {
            bgPaint.color = Color.parseColor(cancelBg)
        } else {
            bgPaint.color = Color.parseColor(normalBg)
        }
    }

    fun addVoiceSize(voiceSize: Int) {
        val message = Message.obtain()
        message.obj = voiceSize
        message.what = WHAT_CHANGE_VOICE_SIZE
        lineHandler!!.sendMessage(message)
    }

    inner class LineHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                WHAT_CHANGE_VOICE_SIZE -> {
                    val voiceSize = msg.obj as Int
                    for (drawLine in arrayList) {
                        drawLine.timeCompletion = 0f
                        drawLine.small = false
                        drawLine.maxSize = (drawLine.rotas * voiceSize).toInt()
                    }
                    sendEmptyMessage(WHAT_ANIMATION)
                }

                WHAT_ANIMATION -> {
                    for (drawLine in arrayList) {
                        val timeStep = 16.0f / drawLine.duration //时间增长步长
                        var timeCompletion = drawLine.timeCompletion //时间完成度
                        timeCompletion += timeStep //更新时间完成度
                        val animationCompletion: Float =
                            mInterpolator.getInterpolation(timeCompletion) //获取动画完成度。
                        //int maxLineSize = (int) (drawLine.rotas * showVoiceSize);
                        var lineSize = 0
                        //更新音量条的高度
                        lineSize = if (drawLine.small) {
                            //变小
                            ((1 - animationCompletion) * drawLine.maxSize).toInt()
                        } else {
                            (animationCompletion * drawLine.maxSize).toInt()
                        }
                        if (timeCompletion >= 1) {
                            //完成了单边的缩小，或增长，则切换模式
                            drawLine.small = !drawLine.small
                            drawLine.timeCompletion = 0f
                        } else {
                            drawLine.timeCompletion = timeCompletion //更新时间完成度。
                        }

                        lineSize = max(lineSize.toDouble(), 10.0).toInt() //对最小值进行过滤
                        val rectF = drawLine.rectF!!
                        rectF.top = -lineSize * 1.0f / 2
                        rectF.bottom = lineSize * 1.0f / 2
                        drawLine.lineSize = lineSize
                    }
                    invalidate() //更新UI
                    removeMessages(WHAT_ANIMATION)
                    sendEmptyMessageDelayed(WHAT_ANIMATION, 16)
                }
            }
        }
    }

    private val WHAT_ANIMATION = 1 //驱动动画的事件
    private val WHAT_BIG = 2 //驱动变宽的事件
    private val WHAT_CHANGE_VOICE_SIZE = 3 //驱动音量条高低变化的事件

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        showWidth = (widthRotas * w).toInt()
        lineHandler!!.sendEmptyMessage(WHAT_ANIMATION)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        canvas.translate((width / 2).toFloat(), (height / 2).toFloat())
        //画背景
        canvas.drawRoundRect(
            (-showWidth / 2).toFloat(),
            (-height / 2).toFloat(),
            (showWidth / 2).toFloat(),
            (height / 2).toFloat(),
            bgRound.toFloat(),
            bgRound.toFloat(),
            bgPaint
        )

        val offsetX: Float = (arrayList.size - 1) * 1.0f / 2 * (LINE_WIDTH + LINE_SPACE)
        canvas.translate(-offsetX, 0f)
        for (drawLine in arrayList) {
            canvas.drawRoundRect(drawLine.rectF!!, 5f, 5f, linePaint)
            canvas.translate((LINE_WIDTH + LINE_SPACE).toFloat(), 0f)
        }
        canvas.restore()
    }

    private val bgRound = 15 //背景圆角的大小,单位dp

    private val LINE_SPACE = 10
}