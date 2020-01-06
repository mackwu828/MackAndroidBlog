package com.mackwu.component.util

import android.os.Environment
import java.io.File
import java.io.InputStream

/**
 * ===================================================
 * Created by MackWu on 2019/12/26 13:58
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * 文件读写。从源文件读取，写入目标文件。File.copyTo内部用了use，use使用了try catch finally，finally里关闭流
 * @param sourceFile 源文件
 * @param destFile 目标文件
 * @param bufferSize 缓存大小默认1024个字节
 */
fun copy(sourceFile: File, destFile: File, bufferSize: Int = 1024) = sourceFile.copyTo(destFile, false, bufferSize)

/**
 * 文件读写。从输入流读取，写入目标文件。InputStream.copyTo内部只是写入，需要外部关闭流，直接用use
 * @param bufferSize 缓存大小默认1024个字节
 */
fun copy(input: InputStream, destFile: File, bufferSize: Int = 1024) = input.use { `in` -> destFile.outputStream().use { out -> `in`.copyTo(out, bufferSize) } }

/**
 * 文件读写。读取字节数组，写入目标文件。InputStream.copyTo内部只是写入，需要外部关闭流，直接用use
 * @param bufferSize 缓存大小默认1024个字节
 */
fun copy(source: ByteArray, destFile: File, bufferSize: Int = 1024) = source.inputStream().use { input -> destFile.outputStream().use { out -> input.copyTo(out, bufferSize) } }
