package com.qwlyz.androidstudy.fragment

import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import com.google.android.exoplayer2.ExoPlayer
import com.qwlyz.androidstudy.databinding.FragmentExoplayerTextBinding

class AudioTextSynchronizer(private val player: ExoPlayer, private val stringList: List<String>, private val binding: FragmentExoplayerTextBinding) {

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
        val currentPosition = player.currentPosition

        // 计算每段文本的显示时间
        val segmentDuration = duration / stringList.size

        // 更新文本内容
        if (currentIndex < stringList.size && currentPosition >= segmentDuration * currentIndex) {
            binding.text.text = stringList[currentIndex]
            currentIndex++
        }
    }
}
