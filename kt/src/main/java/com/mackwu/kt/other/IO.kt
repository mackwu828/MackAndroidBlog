package com.mackwu.kt.other

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * ===================================================
 * Created by MackWu on 2019/12/24 19:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * 文件读写。从源文件读取，写入目标文件。write(byte b[])
 * 比如一个100字节的文件，每次读取1024个字节。写入的时候用的write(byte b[])，那么也写入1024个字节的大小，而不是100个字节。此时目标文件大小是1024个字节
 */
fun io1(sourceFile: File, destFile: File) {
    var fis: FileInputStream? = null
    var fos: FileOutputStream? = null
    try {
        fis = FileInputStream(sourceFile)
        fos = FileOutputStream(destFile)
        val buf = ByteArray(1024)
        while (fis.read(buf) > 0) {
            fos.write(buf)
            fos.flush()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        fis?.close()
        fos?.close()
    }
}

/**
 * 文件读写。从源文件读取，写入目标文件。write(byte b[], int off, int len)
 * 比如一个100字节的文件，每次读取1024个字节。写入的时候用的write(byte b[], int off, int len)，那么会写入len个字节，此时目标文件大小是100个字节
 */
fun io2(sourceFile: File, destFile: File) {
    var fis: FileInputStream? = null
    var fos: FileOutputStream? = null
    try {
        fis = FileInputStream(sourceFile)
        fos = FileOutputStream(destFile)
        val buf = ByteArray(1024)
        var len: Int
        while ((fis.read(buf).also { len = it }) != -1) {
            fos.write(buf, 0, len)
            fos.flush()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        fis?.close()
        fos?.close()
    }
}

/**
 * 文件读写。while break 替代 also
 */
fun io3(sourceFile: File, destFile: File) {
    var fis: FileInputStream? = null
    var fos: FileOutputStream? = null
    try {
        fis = FileInputStream(sourceFile)
        fos = FileOutputStream(destFile)
        val buf = ByteArray(1024)
        while (true) {
            val len = fis.read(buf)
            if (len <= 0) break
            fos.write(buf, 0, len)
            fos.flush()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        fis?.close()
        fos?.close()
    }
}

/**
 * 文件读写。while()
 */
fun io4(sourceFile: File, destFile: File) {
    var fis: FileInputStream? = null
    var fos: FileOutputStream? = null
    try {
        fis = FileInputStream(sourceFile)
        fos = FileOutputStream(destFile)
        val buf = ByteArray(1024)
        var len = fis.read(buf)
        while (len >= 0) {
            fos.write(buf, 0, len)
            fos.flush()
            len = fis.read(buf)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        fis?.close()
        fos?.close()
    }
}


fun main() {
//    val sourceFile = File("C:/Android/text.txt")
//    println("sourceFile exists: " + sourceFile.exists())
//    println("sourceFile isFile: " + sourceFile.isFile)
//    println("sourceFile length: " + sourceFile.length())

    val source = "Hello world!".toByteArray()

    val destFile = File("C:/Android/text2.txt")
    io11(source, destFile)

    println("destFile exists: " + destFile.exists())
    println("destFile isFile: " + destFile.isFile)
    println("destFile length: " + destFile.length())
}


/**
 * 读写文件。通过file可以直接获取io流
 * 通过Closeable.use
 * use函数内部封装了try-catch-finally，在finally里close了io流
 */
fun io10(sourceFile: File, destFile: File) {
    val fis = sourceFile.inputStream()
    val fos = destFile.outputStream()
    val buf = ByteArray(1024)
    fis.use { input ->
        fos.use { output ->
            while (true) {
                val len = input.read(buf)
                if (len <= 0) break
                output.write(buf, 0, len)
                output.flush()
            }
        }
    }
}


/**
 * 读写文件。读取字节数组，写入文件
 */
fun io11(source: ByteArray, destFile: File){
    val bais = source.inputStream()
    val fos = destFile.outputStream()
    val buf = ByteArray(1024)
    bais.use { input ->
        fos.use { output ->
            while (true) {
                val len = input.read(buf)
                if (len <= 0) break
                output.write(buf, 0, len)
                output.flush()
            }
        }
    }
}


/**
 * 读写文件。通过File.forEachBlock
 */
fun io21(sourceFile: File, destFile: File) {
    sourceFile.forEachBlock(1024) { buffer, bytesRead -> destFile.appendBytes(buffer, 0, bytesRead) }
}

/**
 * kotlin1.3只提供了File.writeBytes(array: ByteArray)方法
 */
fun File.writeBytes(buffer: ByteArray, off: Int, len: Int) = FileOutputStream(this).use { it.write(buffer, off, len) }

/**
 * kotlin1.3只提供了appendBytes(array: ByteArray)的方法
 */
fun File.appendBytes(buffer: ByteArray, off: Int, len: Int): Unit = FileOutputStream(this, true).use { it.write(buffer, off, len) }


/**
 * 文件读写。通过io流的copyTo
 */
fun io30(sourceFile: File, destFile: File) {
    sourceFile.inputStream().copyTo(destFile.outputStream(), 1024)
}

/**
 * 文件读写。通过file的copyTo
 */
fun io31(sourceFile: File, destFile: File) {
    sourceFile.copyTo(destFile, false, 1024)
}

/**
 * 文件读写。
 */
fun io32(source: ByteArray, destFile: File){
    source.inputStream().copyTo(destFile.outputStream(), 1024)
}



/**
 * 文件夹
 */
fun io40(sourceFile: File, destFile: File) {
    sourceFile.copyRecursively(destFile, false) { file, ioException ->

        OnErrorAction.SKIP
    }
}


