package com.mackwu.media.play

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.media.R
import com.mackwu.media.util.logD
import kotlinx.android.synthetic.main.audio_activity_play.*

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 17:35
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class PlayActivity : AppCompatActivity() {

    private val player: Play = Player.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.audio_activity_play)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO), 0x01)
        }

        player.setSource(assets.open("list.wav"))
                .setPlayListener(object : PlayListenerAdapter(){
                    override fun onStopped() {
                        logD("onStopped... currentPosition: " + player.currentPosition)
                    }
                })

        btn_start.setOnClickListener {
            player.start()

            /**
             * 添加了多段数据源后，开始播放
             */
//            player.addSource(assets.open("weather.wav"))
//            player.addSource(assets.open("list.wav"))
//            player.addSource(assets.open("music.wav"))
//            player.start()

            /**
             * 开始播放一段数据源后，继续添加数据源
             */
//            player.addSource(assets.open("weather.wav")).start()
//            Thread.sleep(500)
//            player.addSource(assets.open("list.wav")).start()
//            Thread.sleep(500)
//            player.addSource(assets.open("music.wav")).start()
        }
        btn_stop.setOnClickListener { player.stop() }
        btn_pause.setOnClickListener { player.pause() }
        btn_resume.setOnClickListener { player.resume() }
        btn_release.setOnClickListener { player.release() }
        btn_seek_to.setOnClickListener { player.seekTo(3000) }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}