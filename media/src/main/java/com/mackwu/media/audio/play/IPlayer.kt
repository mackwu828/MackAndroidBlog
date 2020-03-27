package com.mackwu.media.audio.play

/**
 * ===================================================
 * Created by MackWu on 2019/12/27 17:05
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
interface IPlayer : Source {

    /**
     * 播放状态
     */
    val state: PlayState

    /**
     * 数据源总时长
     */
    val duration: Int

    /**
     * 数据源当前播放位置
     */
    val currentPosition: Int

    /**
     * 开始播放
     */
    fun start()

    /**
     * 停止播放
     */
    fun stop()

    /**
     * 暂停播放
     */
    fun pause()

    /**
     * 恢复播放
     */
    fun resume()

    /**
     * 释放资源
     */
    fun release()

    /**
     * 指定播放位置
     */
    fun seekTo(duration: Int)

    /**
     * 设置监听
     */
    fun setPlayListener(listener: PlayListener?): IPlayer
}