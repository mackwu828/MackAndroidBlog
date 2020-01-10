package com.mackwu.media.play

import java.io.InputStream

/**
 * ===================================================
 * Created by MackWu on 2020/1/8 16:21
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
interface Source {

    /**
     * 文件夹路径
     */
    val sourceDirPath: String

    /**
     * 文件名称
     */
    val sourceChild: String

    /**
     * 添加数据源
     */
    fun addSource(input: InputStream): Play

    fun addSource(sourceBytes: ByteArray): Play

    fun addSource(sourcePath: String): Play

    /**
     * 设置数据源
     */
    fun setSource(input: InputStream): Play

    fun setSource(sourceBytes: ByteArray): Play

    fun setSource(sourcePath: String): Play

    /**
     * 清除数据源
     */
    fun deleteSource()
}