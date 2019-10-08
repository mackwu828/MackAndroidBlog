package com.mackwu.activity.start

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.activity.R

/**
 * ================================================
 * Created by MackWu on 2019/8/22 19:08
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class TargetActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        /**
         * 启动Activity的最佳方式
         */
        fun start(context: Context, param: String){
            val intent = Intent(context, TargetActivity::class.java)
            intent.putExtra("param", param)
            context.startActivity(intent)
        }
    }

}