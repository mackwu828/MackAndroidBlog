package com.mackwu.component.util.kt

import java.io.File
import java.io.InputStream

/**
 * ===================================================
 * Created by MackWu on 2019/12/26 10:51
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
/**
 * 文件读写。从输入流读取，写入目标文件。InputStream.copyTo内部只是写入，需要外部关闭流，直接用use
 * @param input 输入流
 * @param destFile 目标文件
 * @param bufferSize 缓存大小默认1024个字节
 */
fun copy(input: InputStream, destFile: File, bufferSize: Int = 1024) = input.use { `in` -> destFile.outputStream().use { out -> `in`.copyTo(out, bufferSize) } }


/**
 * 文件读写。从源文件读取，写入目标文件。File.copyTo内部用了use，use使用了try catch finally，finally里关闭流
 * @param sourceFile 源文件
 * @param destFile 目标文件
 * @param bufferSize 缓存大小默认1024个字节
 */
fun copy(sourceFile: File, destFile: File, bufferSize: Int = 1024) = copy(sourceFile.inputStream(), destFile, bufferSize)
fun copyTo(sourceFile: File, destFile: File, bufferSize: Int = 1024) = sourceFile.copyTo(destFile, false, bufferSize)


/**
 * 文件读写。读取字节数组，写入目标文件。InputStream.copyTo内部只是写入，需要外部关闭流，直接用use
 * @param source 字节数组
 * @param destFile 目标文件
 * @param bufferSize 缓存大小默认1024个字节
 */
fun copy(source: ByteArray, destFile: File, bufferSize: Int = 1024) = copy(source.inputStream(), destFile, bufferSize)