package com.mackwu.media.audio.play

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.PowerManager
import com.mackwu.media.util.logD
import java.util.concurrent.Executors

/**
 * ===================================================
 * Created by MackWu on 2019/12/27 18:03
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class MultiPlayer private constructor(private val context: Context) : BasePlayer(context), MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, AudioManager.OnAudioFocusChangeListener, MediaPlayer.OnSeekCompleteListener {
    override val duration: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val currentPosition: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    private val executorService = Executors.newFixedThreadPool(20)
    private var currentPlayer: MediaPlayer? = null
    private var nextPlayer: MediaPlayer? = null
    private var tempPlayer: MediaPlayer? = null

    /**
     * setWakeMode(context.applicationContext, PowerManager.PARTIAL_WAKE_LOCK): 设置设备进入锁状态模式-可在后台播放或者缓冲音乐-CPU一直工作
     */
    private fun MediaPlayer.init() {
        setWakeMode(context.applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
        setAudioStreamType(AudioManager.STREAM_MUSIC)
        setOnCompletionListener(this@MultiPlayer)
        setOnErrorListener(this@MultiPlayer)
        setOnSeekCompleteListener(this@MultiPlayer)
    }

    override fun start() {
        logD(TAG, "start... $state")
        try {
            synchronized(this) {
                if (sourceQueue.isEmpty()) {
                    logD(TAG, "sourceQueue is empty, please set or add media source!")
                    return
                }
                if (state == PlayState.PREPARED || state == PlayState.PLAYING || state == PlayState.PAUSED) {
                    initNextPlayer()
                    return
                }

                // init current
                currentPlayer = MediaPlayer()
                currentPlayer?.init()
                tempPlayer = currentPlayer
                playerQueue.add(tempPlayer!!)

                currentPlayer?.reset()
                currentPlayer?.setDataSource(sourceQueue[0].absolutePath)

                state = PlayState.PREPARING
                listener?.onPreparing()
                currentPlayer?.prepare()

                state = PlayState.PREPARED
                listener?.onPrepared()

                state = PlayState.PLAYING
                currentPlayer?.start()
                listener?.onPlaying()
                startSendingProgress()

                // init next
                initNextPlayer()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            state = PlayState.ERROR
            listener?.onError("start error: " + e.message)
        }
    }

    private fun initNextPlayer() {
        executorService.submit {
            for (i in playerQueue.size until sourceQueue.size) {
                nextPlayer = MediaPlayer()
                nextPlayer?.init()
                nextPlayer?.setDataSource(sourceQueue[i].absolutePath)
                nextPlayer?.prepare()

                tempPlayer?.setNextMediaPlayer(nextPlayer)
                tempPlayer = nextPlayer
                playerQueue.add(nextPlayer!!)
            }
        }
    }

    override fun pause() {
        logD(TAG, "pause... $state")
        if (state == PlayState.PLAYING) {
            currentPlayer?.pause()
            state = PlayState.PAUSED
            listener?.onPaused()
        }
    }

    override fun resume() {
        logD(TAG, "resume... $state")
        if (state == PlayState.PAUSED) {
            currentPlayer?.start()
            state = PlayState.PLAYING
            listener?.onPlaying()
            startSendingProgress()
        }
    }

    override fun stop() {
        logD(TAG, "stop... $state")
        if (state == PlayState.PREPARED || state == PlayState.PLAYING || state == PlayState.PAUSED) {
            currentPlayer?.stop()
            state = PlayState.STOPPED
            listener?.onStopped()
        }
//        am.abandonAudioFocus(null);
    }

    override fun onCompletion(mp: MediaPlayer) {
        logD(TAG, "onCompletion... $state")
        state = PlayState.COMPLETED
        listener?.onCompleted()

        if (sourceQueue.isNotEmpty()) sourceQueue.removeAt(0)
        if (playerQueue.isNotEmpty()) playerQueue.removeAt(0)
        if (playerQueue.isNotEmpty()) {
            currentPlayer = playerQueue[0]
            state = PlayState.PLAYING
        }
    }

    override fun onError(mp: MediaPlayer, what: Int, extra: Int): Boolean {
        logD(TAG, "onError... what:$what, extra: $extra")
        state = PlayState.ERROR
        listener?.onError("$what $extra")
        return false
    }

    override fun release() {
        logD(TAG, "release...")
        if (state == PlayState.STOPPED || state == PlayState.COMPLETED) {
            state = PlayState.RELEASED
            listener?.onRelease()
            currentPlayer = null
            nextPlayer = null
            tempPlayer = null
        }
    }

    override fun seekTo(duration: Int) {
        if (state == PlayState.PREPARED || state == PlayState.PLAYING || state == PlayState.PAUSED) {
            currentPlayer?.seekTo(duration)
            state = PlayState.PREPARING
            listener?.onSeeking()
        }
    }

    override fun onAudioFocusChange(focusChange: Int) {
        logD(TAG, "onAudioFocusChange...  focusChange: $focusChange")
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
            val duration = currentPlayer?.duration ?: 0
            val currentPosition = currentPlayer?.currentPosition ?: 0
            logD(TAG, "duration:$duration, currentPosition: $currentPosition")
            listener?.onProgress(currentPosition, duration)
            handler.postDelayed({ startSendingProgress() }, 1000)
            return
        }
        handler.removeCallbacksAndMessages(0)
    }

//    private fun requestAudioFocus() {
//        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//        } else {
//            audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN)
//        }
//    }

    companion object : SingletonHolder<MultiPlayer, Context>(::MultiPlayer){
        private val TAG = MultiPlayer::class.java.simpleName
    }
}