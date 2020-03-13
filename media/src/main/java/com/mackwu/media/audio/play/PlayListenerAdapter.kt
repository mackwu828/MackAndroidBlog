package com.mackwu.media.audio.play

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 19:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
open class PlayListenerAdapter : PlayListener{
    override fun onPreparing() {
    }

    override fun onPrepared() {
    }

    override fun onPlaying() {
    }

    override fun onPaused() {
    }

    override fun onStopped() {
    }

    override fun onCompleted() {
    }

    override fun onError(message: String) {
    }

    override fun onRelease() {
    }

    override fun onSeeking() {
    }

    override fun onProgress(duration: Int, currentPosition: Int) {
    }
}