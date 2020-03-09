package com.mackwu.http

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val okHttpClient = OkHttpClient.Builder().build()

        btn_dev.setOnClickListener {
            Log.d("TAG", "btn_dev...")
            val url = "https://dev-smarttv.zeasn.tv/BluePortServlets/test/ping"
            val request = Request.Builder().url(url).build()
            okHttpClient.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d("TAG", response.body()?.string())
                }
            })
        }

        btn_pro.setOnClickListener {
            Log.d("TAG", "btn_pro...")
            val url = "https://smarttv.zeasn.tv/BluePortServlets/test/ping"
            val request = Request.Builder().url(url).build()
            okHttpClient.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d("TAG", response.body()?.string())
                }
            })
        }
    }
}
