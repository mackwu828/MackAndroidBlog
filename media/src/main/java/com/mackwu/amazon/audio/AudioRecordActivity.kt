package com.mackwu.amazon.audio

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.amazon.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileOutputStream

/**
 * ===================================================
 * Created by MackWu on 2019/10/14 15:53
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * AudioRecord
 * AudioRecord是Android系统提供的用于实现录音的功能类
 *
 *
 * <h2>初始化</h2>
 * AudioRecord(int audioSource, int sampleRateInHz, int channelConfig, int audioFormat, int bufferSizeInBytes)
 * @see init
 *
 * audioSource：音频源
 * @see MediaRecorder.AudioSource.DEFAULT 默认声音
 * @see MediaRecorder.AudioSource.MIC 麦克风声音。通常用这个
 * @see MediaRecorder.AudioSource.VOICE_UPLINK  通话上行声音
 * ...
 *
 * sampleRateInHz：采样率
 * 每秒钟能够采样的次数，采样率越高，音质越高。给出的实例是44100、22050、11025但不限于这几个参数。例如要采集低质量的音频就可以使用4000、8000等低采样率。
 *
 * channelConfig：声道
 * android支持双声道立体声和单声道。MONO单声道，STEREO立体声
 * @see AudioFormat.CHANNEL_IN_MONO 单声道
 * @see AudioFormat.CHANNEL_IN_STEREO 立体声
 *
 * audioFormat：编码制式
 * 采集来的数据使用PCM编码。android支持的采样大小16bit 或者8bit。采样大小越大，那么信息量越多，音质也越高。
 * 现在主流的采样大小都是16bit，在低质量的语音传输的时候8bit已经足够了。
 * @see AudioFormat.ENCODING_PCM_16BIT
 * @see AudioFormat.ENCODING_PCM_8BIT
 *
 * bufferSizeInBytes：采集数据缓冲区大小
 * @see AudioRecord.getMinBufferSize(int sampleRateInHz, int channelConfig, int audioFormat) 参数同上
 *
 *
 * <h2>开始录音</h2>
 * @see AudioRecord.startRecording
 * @see startRecording
 */
class AudioRecordActivity : AppCompatActivity() {

    private var audioRecord: AudioRecord? = null
    private var isRecording = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        btn_test.setOnClickListener { startRecording() }
    }

    private fun init() {
        audioRecord = AudioRecord(
                MediaRecorder.AudioSource.MIC,
                16000,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                AudioRecord.getMinBufferSize(16000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT)
        )
    }

    private fun startRecording() {
        if (null == audioRecord) init()
        isRecording = true
        audioRecord!!.startRecording()
    }

    private fun stopRecording() {
        if (null == audioRecord) return
        isRecording = false
        audioRecord!!.stop()
        audioRecord!!.release()
        audioRecord = null
    }

    fun write() {
        val bufferSizeInBytes = AudioRecord.getMinBufferSize(16000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT)
        val data = ByteArray(bufferSizeInBytes)
        val os = FileOutputStream("record")

        Thread {
            while (isRecording) {
                val read = audioRecord!!.read(data, 0, bufferSizeInBytes)
                if (AudioRecord.ERROR_INVALID_OPERATION != read) {
                    os.write(data)
                }
            }
        }.start()
    }
}