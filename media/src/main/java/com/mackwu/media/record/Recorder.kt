package com.mackwu.media.record

/**
 * ===================================================
 * Created by MackWu on 2019/11/26 18:53
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
interface Recorder {

    /**
     * 开始录制
     * Start recording process.
     */
    fun start()

    /**
     * 停止录制
     * Stop recording process.
     */
    fun stop()

    /**
     * 暂停录制
     * Pause recording process.
     */
    fun pause()

    /**
     * 恢复录制
     * Resume recording process.
     */
    fun resume()

}