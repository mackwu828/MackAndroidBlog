package com.mackwu.media.audio.play

import java.io.InputStream

/**
 * ===================================================
 * Created by MackWu on 2020/1/10 15:32
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
interface MultiSource {

    /**
     * 添加数据源
     */
    fun addSource(input: InputStream): IPlayer

    fun addSource(sourceBytes: ByteArray): IPlayer

    fun addSource(sourcePath: String): IPlayer

}