package com.qwlyz.androidstudy.fragment

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.qwlyz.androidstudy.AudioTrackPlayer
import com.qwlyz.androidstudy.databinding.FragmentExoplayerTextBinding

class AudioTextSynchronizer2(private val player: AudioTrackPlayer, private val stringList: List<String>, private val binding: FragmentExoplayerTextBinding) {

    private var currentIndex = 0
    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 1000L // 每秒更新一次
    private val runnable = object : Runnable {
        override fun run() {
            updateText()
            handler.postDelayed(this, updateInterval)
        }
    }

    fun startUpdatingText() {
        handler.post(runnable)
    }

    fun stopUpdatingText() {
        handler.removeCallbacks(runnable)
    }

    private fun updateText() {
        val duration = player.duration
        val currentPosition = player.getCurrentPosition()
        Log.d("ExoplayerTextFragment", "updateText.updateText: $duration")

        // 计算每段文本的显示时间
        val segmentDuration = duration / stringList.size

        // 更新文本内容
        if (currentIndex < stringList.size && currentPosition >= segmentDuration * currentIndex) {
            binding.text.text = stringList[currentIndex]
            currentIndex++
        }
    }
}
