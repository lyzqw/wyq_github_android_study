package com.qwlyz.androidstudy

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioFormat.CHANNEL_OUT_MONO
import android.media.AudioFormat.ENCODING_PCM_16BIT
import android.media.AudioTrack
import android.os.SystemClock

class AudioTrackPlayer {

    private var audioTrack: AudioTrack?
    private var startTime: Long = 0L
    private val sampleRate = 16000
    private val bytesPerSample = ENCODING_PCM_16BIT // 16-bit PCM
    private val channelCount = CHANNEL_OUT_MONO // Mono

    init {
        audioTrack = AudioTrack.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            .setAudioFormat(
                AudioFormat.Builder()
                    .setEncoding(bytesPerSample)
                    .setSampleRate(sampleRate)
                    .setChannelMask(channelCount)
                    .build()
            )
            .build()
        audioTrack!!.play()
    }

    fun play(pcm: String) {
        val hexStringToByteArray = hexStringToByteArray(pcm)
        audioTrack?.notificationMarkerPosition = hexStringToByteArray.size
        audioTrack?.positionNotificationPeriod = hexStringToByteArray.size
        audioTrack!!.write(hexStringToByteArray, 0, hexStringToByteArray.size)
        startTime = SystemClock.elapsedRealtime()
    }

    fun play(hexStringToByteArray: ByteArray) {
//        val hexStringToByteArray = hexStringToByteArray(pcm)
//        audioTrack?.notificationMarkerPosition = hexStringToByteArray.size
//        audioTrack?.positionNotificationPeriod = hexStringToByteArray.size
        audioTrack!!.write(hexStringToByteArray, 0, hexStringToByteArray.size)
        startTime = SystemClock.elapsedRealtime()
    }

    private fun calculateDuration(byteArraySize: Int, sampleRate: Int, bytesPerSample: Int, channelCount: Int): Long {
        return (byteArraySize / (sampleRate * bytesPerSample * channelCount)).toLong()
    }

    fun getCurrentPosition(): Long {
        val elapsedTime = SystemClock.elapsedRealtime() - startTime
        return (elapsedTime * sampleRate * bytesPerSample * channelCount / 1000).toLong()
    }

    private fun hexStringToByteArray(hexString: String): ByteArray {
        val len = hexString.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(hexString[i], 16) shl 4) +
                    Character.digit(hexString[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }

    fun release() {
        if (audioTrack != null) {
            audioTrack!!.stop()
            audioTrack!!.release()
            audioTrack = null
        }
    }
}
