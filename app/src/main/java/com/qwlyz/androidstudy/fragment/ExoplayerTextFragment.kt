package com.qwlyz.androidstudy.fragment

import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.ThreadUtils
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.qwlyz.androidstudy.BaseFragment
import com.qwlyz.androidstudy.R
import com.qwlyz.androidstudy.databinding.FragmentExoplayerTextBinding
import com.yuwq.libs_common.viewBinding
import java.io.InputStream

/**
 *
 * @author lyz
 */
class ExoplayerTextFragment : BaseFragment() {
    companion object{
        const val TAG = "ExoplayerTextFragment"
    }


    lateinit var player: ExoPlayer

    private val binding by viewBinding(FragmentExoplayerTextBinding::bind)

    override fun getLayoutId(): Int = R.layout.fragment_exoplayer_text
    lateinit var audioTextSynchronizer: AudioTextSynchronizer
    lateinit var stringList: List<String>

    override fun initData() {
        binding.btnExpand.setOnClickListener {

            stringList = splitTextToSegments(
                "阿九速度快放假乐凯大街法拉克时间点法拉克法律卡倒计时领导反馈就爱上了咖啡机离开家阿是发来的看见啊看见千图网二哟i一样我确认剖他怕青蛙突破我饿u他一拉会计审计法干撒发卡机啊哈开发垃圾发啦美女吧美女吧你现在你走吧没拿咱爸妈你把v这把MV仔细你马爸爸v没咋v拉胯束带结发灰色轨迹哈搜嘎哈卡手机哈工卡叫啥考拉海发卡机合法卡机说法回答了哦起舞额UR哦恶意图群体哦曲儿一起饿哦i我容易去污染一起哦为任意阒然伊娃IQ",
                binding.text
            )

            player = ExoPlayer.Builder(requireContext()).build()
            val dataSourceFactory = DefaultDataSourceFactory(requireContext(), "your-user-agent")
            val mediaSourceFactory = DefaultMediaSourceFactory(dataSourceFactory)
            val mediaItem = MediaItem.Builder().setUri("asset:///calling.mp3").build()
            player.setMediaSource(mediaSourceFactory.createMediaSource(mediaItem))
            player.prepare()
            player.play()



            audioTextSynchronizer = AudioTextSynchronizer(player, stringList, binding)

            // Add a listener to update text based on playback
            player.addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    if (playbackState == Player.STATE_READY) {
                        player.playWhenReady = true
                        Log.d(TAG, "startUpdatingText: ")
                        audioTextSynchronizer.startUpdatingText()
                    } else if (playbackState == Player.STATE_ENDED){
                        Log.d(TAG, "stopUpdatingText: ")
                        audioTextSynchronizer.stopUpdatingText()
                    }
                }

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    Log.d(TAG, "onIsPlayingChanged: $isPlaying")
                    if (isPlaying) {
                        updateTextBasedOnPlayback()
                    }
                }

                override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                    super.onPlayerStateChanged(playWhenReady, playbackState)
                    Log.d(TAG, "onPlaybackStateChanged.currentPosition: $playbackState")

//                    val duration = player.duration
//                    val currentPosition = player.currentPosition
//                    Log.d(TAG, "onPlaybackStateChanged.duration: $duration")
//                    binding.text.text = stringList[currentIndex]
//                    currentIndex++
//                    if (currentPosition > duration / stringList.size * currentIndex && currentIndex < stringList.size) {
//                    }
                }



            })

        }
    }


    private var currentIndex = 0

    private fun updateTextBasedOnPlayback() {
        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }


    fun splitTextToSegments(fullText: String, textView: TextView): List<String> {
        // 获取屏幕宽度
        val screenWidth = ScreenUtils.getAppScreenWidth()

        // 创建 Paint 对象用于测量文本宽度
        val textPaint = Paint()
        textPaint.textSize = textView.textSize  // 设置 Paint 的文本尺寸与 TextView 保持一致

        // 初始化变量
        val segments = mutableListOf<String>()
        var remainingText = fullText

        while (remainingText.isNotEmpty()) {
            // 逐字符增加测量，直到超出屏幕宽度
            var endIndex = 0
            while (endIndex < remainingText.length && textPaint.measureText(
                    remainingText.substring(
                        0,
                        endIndex + 1
                    )
                ) <= screenWidth
            ) {
                endIndex++
            }

            // 获取当前行
            val currentLine = remainingText.substring(0, endIndex)

            // 移除已处理的部分
            remainingText = remainingText.substring(endIndex).trimStart()

            // 将行添加到段落中，每两行合并成一个段落
            segments.add(currentLine)
        }

        // 将两行组合为一个段落
        return segments.chunked(2) { it.joinToString("\n") }
    }

}