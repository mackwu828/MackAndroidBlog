package com.mackwu.media.audio.play

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 17:53
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
enum class PlayState {

    /**
     * 调用 new MediaPlayer() 时处于该状态
     * 调用 reset() 时处于该状态
     */
    IDLE,

    /**
     * 调用 setDataSource() 时处于该状态
     */
    INITIALIZED,

    /**
     * 调用 prepareAsync() 时处于该状态
     */
    PREPARING,

    /**
     * 调用 prepare() 时处于该状态。同步
     * 调用 prepareAsync() 时，在 onPrepared() 回调时处于该状态。异步
     */
    PREPARED,

    /**
     * 调用 start() 时处于该状态
     */
    PLAYING,

    /**
     * 调用 paused() 时处于该状态
     */
    PAUSED,

    /**
     * 调用 stop() 时处于该状态。处于该状态时，需要调用 prepareAsync() 或者 prepare() 回到先前的 PREPARED 状态才可以重写是 start()
     */
    STOPPED,

    /**
     * 调用 release() 时处于该状态。一旦MediaPlayer对象被 release , 它不能再被使用，也没有办法再迁移到其它状态，需要创建新的MediaPlayer对象
     */
    RELEASED,

    /**
     * onCompletion() 回调时处于该状态。非循环状态下，可以再调用 start() 方法来让这个MediaPlayer对象再进入 playing 状态。
     */
    COMPLETED,

    /**
     * onError() 回调时处于该状态。为了重用一个处于Error状态的MediaPlayer对象，可以调用 reset() 方法来把这个对象恢复成 Idle 状态。
     */
    ERROR,

}