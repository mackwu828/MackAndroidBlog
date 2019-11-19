package com.mackwu.amazon.audio

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.amazon.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ===================================================
 * Created by MackWu on 2019/10/11 15:50
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 *
 * 音频转文本。 AudioRecord 录制音频 并用本地和Socket2中方式上传
 * 文本转语音。 API获取音频流并用AudioTrack 播放。
 *
 * <h2>录音</h2>
 * @see AudioRecordActivity
 *
 */
class AudioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_test.setOnClickListener {  }
    }


}