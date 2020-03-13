package com.mackwu.media.audio.play

import android.content.Context
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.PowerManager
import com.mackwu.media.util.logD


/**
 * ===================================================
 * Created by MackWu on 2019/12/27 18:03
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class Player private constructor(private val context: Context) : BasePlayer(context), MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, AudioManager.OnAudioFocusChangeListener, MediaPlayer.OnSeekCompleteListener {

    private var mediaPlayer: MediaPlayer? = null

    override val duration: Int
        get() = mediaPlayer?.duration ?: -1

    override val currentPosition: Int
        get() = mediaPlayer?.currentPosition ?: -1

    override fun start() {
        logD(TAG, "start... state: $state")
        try {
            if (sourceQueue.isEmpty()) {
                logD(TAG, "sourceQueue is empty, please set or add media source!")
                return
            }

            // 创建 mediaPlayer 时设置为 IDLE 状态
            if (null == mediaPlayer) {
                logD(TAG, "init player...")
                mediaPlayer = MediaPlayer()
                state = PlayState.IDLE
                mediaPlayer?.setWakeMode(context.applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
                mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
                mediaPlayer?.setOnPreparedListener(this)
                mediaPlayer?.setOnCompletionListener(this)
                mediaPlayer?.setOnErrorListener(this)
                mediaPlayer?.setOnSeekCompleteListener(this)
                mediaPlayer?.setOnBufferingUpdateListener(this)
            }

            when (state) {
                // 只有处于 IDLE 状态，才可以调用 setDataSource() ，否则会出现 IllegalStateException
                // 如果处于 ERROR 状态，调用 reset() 重置状态后，再调用 setDataSource()
                PlayState.IDLE, PlayState.ERROR -> {
                    if (state == PlayState.ERROR) {
                        mediaPlayer?.reset()
                        state = PlayState.IDLE
                    }
                    mediaPlayer?.setDataSource(sourceQueue[0].absolutePath)
                    state = PlayState.INITIALIZED

                    mediaPlayer?.prepareAsync()
                    state = PlayState.PREPARING
                    listener?.onPreparing()
                }
                PlayState.INITIALIZED -> {
                }
                PlayState.PREPARING -> {
                }
                PlayState.PREPARED -> {
                }
                PlayState.PLAYING -> {
                }
                PlayState.PAUSED -> {
                }
                // 如果处于 STOPPED 状态，重新 prepareAsync()
                PlayState.STOPPED -> {
                    mediaPlayer?.prepareAsync()
                    state = PlayState.PREPARING
                    listener?.onPreparing()
                }
                PlayState.RELEASED -> {
                }
                PlayState.COMPLETED -> {
                    mediaPlayer?.start()
                    state = PlayState.PLAYING
                    listener?.onPlaying()
                    startSendingProgress()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            state = PlayState.ERROR
            listener?.onError("start error: " + e.message)
        }
    }

    override fun pause() {
        logD(TAG, "pause... state: $state")
        if (state == PlayState.PLAYING) {
            mediaPlayer?.pause()
            state = PlayState.PAUSED
            listener?.onPaused()
        }
    }

    override fun resume() {
        logD(TAG, "resume... state: $state")
        if (state == PlayState.PAUSED) {
            mediaPlayer?.start()
            state = PlayState.PLAYING
            listener?.onPlaying()
            startSendingProgress()
        }
    }

    override fun stop() {
        logD(TAG, "stop... state: $state")
        if (state == PlayState.PREPARED || state == PlayState.PLAYING || state == PlayState.PAUSED) {
            mediaPlayer?.stop()
            state = PlayState.STOPPED
            listener?.onStopped()
        }
    }

    override fun release() {
        logD(TAG, "release... state: $state")
        if (state == PlayState.IDLE || state == PlayState.STOPPED || state == PlayState.COMPLETED) {
            mediaPlayer?.release()
            mediaPlayer = null
            state = PlayState.RELEASED
            listener?.onRelease()
        }
    }

    override fun seekTo(duration: Int) {
        logD(TAG, "seekTo... state: $state")
        if (state == PlayState.PREPARED || state == PlayState.PLAYING || state == PlayState.PAUSED) {
            mediaPlayer?.seekTo(duration)
            state = PlayState.PREPARING
            listener?.onSeeking()
        }
    }

    override fun onPrepared(mp: MediaPlayer) {
        logD(TAG, "onPrepared...")
        try {
            requestAudioFocus()

            state = PlayState.PREPARED
            listener?.onPrepared()

            mp.start()
            state = PlayState.PLAYING
            listener?.onPlaying()
            startSendingProgress()
        } catch (e: Exception) {
            e.printStackTrace()
            state = PlayState.ERROR
            listener?.onError("prepared error: " + e.message)
        }
    }

    /**
     * 播放网络media的时候回调
     */
    override fun onBufferingUpdate(mp: MediaPlayer?, percent: Int) {
        logD(TAG, "onBufferingUpdate... percent: $percent")
    }

    override fun onCompletion(mp: MediaPlayer) {
        logD(TAG, "onCompletion...")
        state = PlayState.COMPLETED
        listener?.onCompleted()
    }

    override fun onError(mp: MediaPlayer, what: Int, extra: Int): Boolean {
        logD(TAG, "onError... what:$what, extra: $extra")
        state = PlayState.ERROR
        listener?.onError("$what $extra")
        return false
    }

    override fun onAudioFocusChange(focusChange: Int) {
        logD(TAG, "onAudioFocusChange...  focusChange: $focusChange")
        when (focusChange) {
            // 获取 audio focus
            AudioManager.AUDIOFOCUS_GAIN -> {
                resume()
                mediaPlayer?.setVolume(1.0f, 1.0f)
            }
            // 失去 audio focus 很长一段时间，必须停止所有的 audio 播放，清理资源
            AudioManager.AUDIOFOCUS_LOSS -> {
                stop()
                release()
            }
            // 暂时失去 audio focus，但是很快就会重新获得，在此状态应该暂停所有音频播放，但是不能清除资源
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> pause()
            // 暂时失去 audio focus，但是允许持续播放音频(以很小的声音)，不需要完全停止播放。
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> mediaPlayer?.setVolume(0.1f, 0.1f)
        }
    }

    override fun onSeekComplete(mp: MediaPlayer) {
        logD(TAG, "onSeekComplete...")
        mp.start()
        state = PlayState.PLAYING
        listener?.onPlaying()
        startSendingProgress()
    }

    /**
     * 播放进度
     */
    private fun startSendingProgress() {
        if (state == PlayState.PLAYING) {
            val currentPosition = mediaPlayer?.currentPosition ?: 0
            val duration = mediaPlayer?.duration ?: 0
            logD(TAG, "currentPosition: $currentPosition")
            listener?.onProgress(currentPosition, duration)
            handler.postDelayed({ startSendingProgress() }, 1000)
            return
        }
        handler.removeCallbacksAndMessages(0)
    }

    /**
     * 处理音频焦点。处理多个程序会来竞争音频输出设备
     */
    private fun requestAudioFocus() {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val audioFocusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                    .setOnAudioFocusChangeListener(this)
                    .build()
            audioFocusRequest.acceptsDelayedFocusGain()
            audioManager.requestAudioFocus(audioFocusRequest)
        } else {
            audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN)
        }
    }

    companion object : SingletonHolder<Player, Context>(::Player) {
        private val TAG = Player::class.java.simpleName
    }
}