package com.mackwu.media.play

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
    fun addSource(input: InputStream): Play

    fun addSource(sourceBytes: ByteArray): Play

    fun addSource(sourcePath: String): Play

}