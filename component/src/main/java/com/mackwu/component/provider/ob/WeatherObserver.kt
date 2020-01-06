package com.mackwu.component.provider.ob

import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.util.Log

/**
 * ===================================================
 * Created by MackWu on 2019/12/30 16:21
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class WeatherObserver(val context: Context, handler: Handler) : ContentObserver(handler) {

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        Log.d(TAG, "onChange...")
    }

    override fun onChange(selfChange: Boolean, uri: Uri?) {
        super.onChange(selfChange, uri)
        Log.d(TAG, "onChange... uri: $uri")
        if (null == uri) return
        val cursor = context.contentResolver.query(Uri.parse("content://cn.zeasn.weather.query"), null, null, null, null)
        cursor?.run {
            moveToNext()
            val generalWeather = cursor.getString(cursor.getColumnIndex("forecasts"))
            Log.d(TAG, "generalWeather: $generalWeather")
            close()
        }
    }

    companion object {
        private val TAG = WeatherObserver::class.java.simpleName
    }
}