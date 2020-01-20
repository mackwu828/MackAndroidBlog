package com.mackwu.media.play

import android.content.Context
import android.media.MediaPlayer
import android.os.Environment
import android.os.Handler
import android.os.Looper
import com.mackwu.media.util.copy
import com.mackwu.media.util.logD
import java.io.File
import java.io.InputStream

/**
 * ===================================================
 * Created by MackWu on 2020/1/8 16:12
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
abstract class BasePlayer(private val context: Context) : Play {

    protected val handler = Handler(Looper.getMainLooper())
    protected val sourceQueue = arrayListOf<File>()
    protected val playerQueue = arrayListOf<MediaPlayer>()
    protected var listener: PlayListener? = null

    override var state = PlayState.IDLE

    override val sourceDirPath: String
        get() = Environment.getExternalStorageDirectory().absolutePath + File.separator + context.packageName + File.separator + "audio"

    override val sourceChild: String
        get() = System.currentTimeMillis().toString() + ".mp3"

    override fun setPlayListener(listener: PlayListener?): Play {
        this.listener = listener
        return this
    }

    override fun setSource(input: InputStream): Play {
        logD(TAG, "setSource...")
        // clear
        sourceQueue.clear()

        // 文件夹
        logD(TAG, "sourceDirPath: $sourceDirPath")
        val dir = File(sourceDirPath)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        logD(TAG, "dir exists: " + dir.exists())
        logD(TAG, "dir length: " + dir.length())

        // 文件
        val source = File(sourceDirPath, sourceChild)

        // copy
        copy(input, source)
        logD(TAG, "source exists: " + source.exists())
        logD(TAG, "source isFile: " + source.isFile)
        logD(TAG, "source length: " + source.length())

        // add
        sourceQueue.add(source)
        return this
    }

    override fun setSource(sourceBytes: ByteArray): Play {
        setSource(sourceBytes.inputStream())
        return this
    }

    override fun setSource(sourcePath: String): Play {
        setSource(File(sourcePath).inputStream())
        return this
    }

    override fun deleteSource() {
        logD("deleteSource...")
        if (sourceQueue.isNotEmpty()) {
            for (file in sourceQueue) {
                if (file.exists()) file.delete()
            }
            sourceQueue.clear()
        }
    }

    companion object {
        private val TAG = BasePlayer::class.java.simpleName
    }
}