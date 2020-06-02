package com.mackwu.component.other

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.component.R

/**
 * ===================================================
 * Created by MackWu on 2019/10/8 14:37
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 计时器
 *
 * <h2>CountDownTimer</h2>
 * CountDownTimer(long millisInFuture, long countDownInterval)
 * millisInFuture: 倒计时的总时长。单位毫秒
 * countDownInterval: 每次的间隔时间。单位毫秒
 */
class TimerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val countDownTimer = object : CountDownTimer(60_000, 1000){
            /**
             * 倒计时结束
             */
            override fun onFinish() {

            }

            /**
             * 每次间隔时回调
             */
            override fun onTick(millisUntilFinished: Long) {

            }
        }
        countDownTimer.start()
    }
}
