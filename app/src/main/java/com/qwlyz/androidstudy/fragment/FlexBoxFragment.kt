package com.qwlyz.androidstudy.fragment

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.media.MediaRecorder
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout.HORIZONTAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.databinding.FragmentCardBinding
import com.qwlyz.androidstudy.databinding.FragmentFlexBinding
import com.qwlyz.androidstudy.ext.getRandomColor
import com.yuwq.libs_common.viewBinding


/**
 *
 * @author lyz
 */
class FlexBoxFragment : BaseFragment() {

    private val binding by viewBinding(FragmentFlexBinding::bind)


    override fun getLayoutId(): Int = R.layout.fragment_flex


    override fun initView() {
        setRecyclerView()

        binding.imvRecordAudio.setOnTouchListener { v, event ->
            handleAudioTouchEvent(event, v)
            return@setOnTouchListener false
        }
    }

    private var started = false
    private var touched = false
    private var shouldCancel = false

    private fun handleAudioTouchEvent(event: MotionEvent, v: View) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                initAudioRecord()
                onStartAudioRecord()
                touched = true
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                onEndAudioRecord(isAudioShouldCancel(v, event))
                touched = false
            }

            MotionEvent.ACTION_MOVE -> {
                cancelAudioRecord(isAudioShouldCancel(v, event))
                touched = true
            }
        }
    }

    private fun initAudioRecord() {

    }

    private fun isAudioShouldCancel(view: View, event: MotionEvent): Boolean {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        return event.rawX < location[0] || event.rawX > location[0] + view.width || event.rawY < location[1] - 40
    }


    private fun cancelAudioRecord(audioShouldCancel: Boolean) {
        // reject
        if (!started) {
            return
        }
        // no change
        if (shouldCancel == audioShouldCancel) {
            return
        }
        shouldCancel = audioShouldCancel
//        updateTimerTip(audioShouldCancel)
    }

    private fun onEndAudioRecord(audioShouldCancel: Boolean) {
        started = false
        binding.imvRecordAudio.stop()
//        audioRecorder?.completeRecord(audioShouldCancel)
//        getLayoutPlayAudio().visibility = View.GONE
    }


    private fun onStartAudioRecord() {
        binding.imvRecordAudio.start()
//        activity.window.setFlags(
//            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
//            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//        )
//        audioRecorder?.startRecord()
    }


    private fun setRecyclerView() {
//        val layoutManager = FlexboxLayoutManager(context)
//        layoutManager.flexDirection = FlexDirection.COLUMN
//        layoutManager.flexWrap = FlexWrap.WRAP
//        layoutManager.justifyContent = JustifyContent.FLEX_END

        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(3, HORIZONTAL)

        val list_data = ArrayList<String>()
        for (i in 0..2) {
            list_data.add("71这里");
            list_data.add("2222是设置");
            list_data.add("的子元素");
            list_data.add("的属性");
            list_data.add("设置的");
            list_data.add("属性可以参考");
            list_data.add("上面的介绍。效");
            list_data.add("果图如");
            list_data.add("下");
            list_data.add("2轻轻松松");
        }

        binding.recyclerView.adapter =
            object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_flex, list_data) {
                override fun convert(holder: BaseViewHolder, item: String) {
                    holder.setGone(R.id.view_line, holder.layoutPosition != 1)
                    holder.setText(R.id.text, item)
                    holder.getView<View>(R.id.text).background = getDrawable(getRandomColor())

                }
            }
    }

    fun getDrawable(c: Int): Drawable {
        val drawable = GradientDrawable()
        drawable.setColor(c)
        drawable.cornerRadius = 20f
        return drawable
    }

    override fun initData() {

    }
}