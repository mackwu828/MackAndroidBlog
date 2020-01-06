package com.mackwu.component.provider

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.mackwu.component.R
import com.mackwu.component.provider.ob.WeatherObserver

/**
 * ===================================================
 * Created by MackWu on 2019/12/30 16:18
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class WeatherActivity : AppCompatActivity() {

    private val weatherObserver = WeatherObserver(this, Handler())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // registerContentObserver
        // "content://cn.zeasn.weather.query.query"
        contentResolver.registerContentObserver(Uri.parse("content://cn.zeasn.weather.observer/query"), true, weatherObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        // unregisterContentObserver
        contentResolver.unregisterContentObserver(weatherObserver)
    }
}