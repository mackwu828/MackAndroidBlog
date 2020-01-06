package com.mackwu.media.record

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Environment
import android.support.annotation.NonNull
import android.util.Log
import java.io.File
import java.io.FileOutputStream

/**
 * ===================================================
 * Created by MackWu on 2019/11/25 16:47
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
object Recorder {

    // 音频源。MediaRecorder.AudioSource.DEFAULT 默认声音、MediaRecorder.AudioSource.MIC 麦克风声音，通常用这个、MediaRecorder.AudioSource.VOICE_UPLINK 通话上行声音
    private const val audioSource = MediaRecorder.AudioSource.MIC
    // 采样率。每秒钟能够采样的次数，采样率越高，音质越高。给出的实例是44100、22050、11025但不限于这几个参数。例如要采集低质量的音频就可以使用4000、8000等低采样率。
    private const val sampleRateInHz = 16000
    // 声道。android支持双声道立体声和单声道。AudioFormat.CHANNEL_IN_MONO单声道、AudioFormat.CHANNEL_IN_STEREO立体声
    private const val channelConfig = AudioFormat.CHANNEL_IN_MONO
    // 编码制式。采集来的数据使用PCM编码。android支持的采样大小16bit 或者8bit。采样大小越大，那么信息量越多，音质也越高。
    // 现在主流的采样大小都是16bit，在低质量的语音传输的时候8bit已经足够了。AudioFormat.ENCODING_PCM_16BIT、AudioFormat.ENCODING_PCM_8BIT
    private const val audioFormat = AudioFormat.ENCODING_PCM_16BIT
    // 采集数据缓冲区大小。AudioRecord.getMinBufferSize(int sampleRateInHz, int channelConfig, int audioFormat)参数同上
    private val bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat)
    /**
     * 初始化AudioRecord
     * AudioRecord(int audioSource, int sampleRateInHz, int channelConfig, int audioFormat, int bufferSizeInBytes)
     * audioSource：音频源。MediaRecorder.AudioSource.DEFAULT 默认声音、MediaRecorder.AudioSource.MIC 麦克风声音，通常用这个、MediaRecorder.AudioSource.VOICE_UPLINK 通话上行声音
     * sampleRateInHz：采样率。每秒钟能够采样的次数，采样率越高，音质越高。给出的实例是44100、22050、11025但不限于这几个参数。例如要采集低质量的音频就可以使用4000、8000等低采样率。
     * channelConfig：声道。android支持双声道立体声和单声道。AudioFormat.CHANNEL_IN_MONO单声道、AudioFormat.CHANNEL_IN_STEREO立体声
     * audioFormat：编码制式。采集来的数据使用PCM编码。android支持的采样大小16bit 或者8bit。采样大小越大，那么信息量越多，音质也越高。
     * 现在主流的采样大小都是16bit，在低质量的语音传输的时候8bit已经足够了。AudioFormat.ENCODING_PCM_16BIT、AudioFormat.ENCODING_PCM_8BIT
     * bufferSizeInBytes：采集数据缓冲区大小。AudioRecord.getMinBufferSize(int sampleRateInHz, int channelConfig, int audioFormat)参数同上
     */
    private val audioRecord = AudioRecord(audioSource, sampleRateInHz, channelConfig, audioFormat, bufferSizeInBytes)
    // 录音状态
    private var state = RecordState.STOP

    /**
     * 开始录音
     * public void startRecording()
     */
    private fun startRecording() {
        Log.d("TAG", "startRecording...")
        audioRecord.startRecording()
    }

    /**
     * 停止录音
     * public void stop()
     */
    private fun stopRecording() {
        Log.d("TAG", "stopRecording...")
        audioRecord.stop()
    }

    /**
     * 从录音设备中读取音频数据
     * public int read(@NonNull byte[] audioData, int offsetInBytes, int sizeInBytes)
     * audioData：音频数据写入的byte[]缓冲区
     * offsetInBytes：偏移量
     * sizeInBytes：读取大小
     */
    private fun startReading() {
        val buffer = ByteArray(bufferSizeInBytes)
        val read = audioRecord.read(buffer, 0, buffer.size)
    }

    /**
     * 开始录音流程
     */
    fun startProcess(onAudioSaved: (file: File) -> Unit) {
        Thread {
            try {
                when (state) {
                    RecordState.RECORDING -> {
                    }
                    RecordState.STOP -> {
                    }
                }
                // 开始录音
                startRecording()
                // 开始读取音频数据
                val file = File(Environment.getExternalStorageDirectory(), "recording.pcm")
                val fos = FileOutputStream(file)
                val buffer = ByteArray(bufferSizeInBytes)
                while (state == RecordState.RECORDING) {
                    val read = audioRecord.read(buffer, 0, buffer.size)
                    if (read != AudioRecord.ERROR_INVALID_OPERATION) {
                        fos.write(buffer)
                        fos.flush()
                        Log.d("TAG", "onAudioReading...")
//                        onAudioReading.invoke()
                    } else {
                        // 停止录音
                        stopRecording()
                    }
                }
                if (state == RecordState.STOP) {
                    fos.close()
                    onAudioSaved.invoke(file)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                stopRecording()
            }
        }.start()
    }

}