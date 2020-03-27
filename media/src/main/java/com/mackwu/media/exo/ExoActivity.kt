package com.mackwu.media.exo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.exo_activity.*


/**
 * ===================================================
 * Created by MackWu on 2020/3/13 16:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class ExoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mackwu.media.R.layout.exo_activity)

        val player = ExoPlayerFactory.newSimpleInstance(this, DefaultRenderersFactory(this), DefaultTrackSelector())
        player_view.player = player

        val dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, ""))
//        val videoSource = SingleSampleMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(""), )
//        player.prepare(videoSource)
    }
}