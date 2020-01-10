package com.mackwu.media.play

import android.media.MediaPlayer

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 19:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
interface PlayListener {

    /**
     * 准备中
     */
    fun onPreparing()

    /**
     * 准备完毕
     */
    fun onPrepared()

    /**
     * 播放中
     */
    fun onPlaying()

    /**
     * 播放暂停
     */
    fun onPaused()

    /**
     * 播放停止
     */
    fun onStopped()

    /**
     * 播放完成
     */
    fun onCompleted()

    /**
     * 播放错误
     */
    fun onError(message: String)

    /**
     * 释放资源
     */
    fun onRelease()

    /**
     * 正在指定播放位置
     */
    fun onSeeking()

    /**
     * 当前播放进度
     * @param duration 数据源时长
     * @param currentPosition 当前进度
     */
    fun onProgress(duration: Int, currentPosition: Int)
}