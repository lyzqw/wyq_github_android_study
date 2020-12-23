package com.qwlyz.androidstudy

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var mPopupWindow: PopupWindow
    lateinit var target: View
    lateinit var popView: View
    lateinit var seekBarView: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        target = findViewById<View>(R.id.tv_hello_world)
        val showView = findViewById<View>(R.id.show)


        showView.setOnClickListener {
            initPopWindow()
        }

        findViewById<View>(R.id.update).setOnClickListener {
            Log.d("wqq", "onCreate: update..")
            popView.visibility = View.VISIBLE
//            target.offsetLeftAndRight(100)
//            mPopupWindow.update(target, mPopupWindow.width, mPopupWindow.height)
        }

        findViewById<View>(R.id.dismiss).setOnClickListener {
            popView.visibility = View.GONE
        }


        seekBarView = findViewById(R.id.seekBar)
        seekBarView.progress = 0
        seekBarView.max = 600
        seekBarView.setOnTouchListener { v, event ->

         return@setOnTouchListener false
        }
        seekBarView.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val percent = progress * 1.0 / 600
                val offset = (resources.displayMetrics.widthPixels * percent).toInt()

                val layoutParams = target.layoutParams as LinearLayout.LayoutParams
                layoutParams.leftMargin = offset
                target.layoutParams = layoutParams

                initPopWindow()
                popView.visibility = View.VISIBLE
                mPopupWindow.update(target, mPopupWindow.width, mPopupWindow.height)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                popView.visibility = View.GONE
            }

        })
    }

    private fun initPopWindow() {
        if (::mPopupWindow.isInitialized)return
        mPopupWindow = PopupWindow(this)

        popView = layoutInflater.inflate(R.layout.layout_popowindow, null)
        val layoutParams =
                ViewGroup.LayoutParams(
                        resources.displayMetrics.widthPixels,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        popView.layoutParams = layoutParams
        mPopupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mPopupWindow.contentView = popView
        val height =  - 150
        popView.visibility = View.GONE
        mPopupWindow.showAsDropDown(target, 0, height)
    }
}