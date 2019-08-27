package com.mackwu.viewcustom.event

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import com.mackwu.viewcustom.R

/**
 * ================================================
 * Created by MackWu on 2019/8/15 18:11
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * 屏幕是用户和Android设备交互的主要媒介，屏幕分为触屏和非触屏。
 * 触屏设备。如手机、平板等，用户主要通过手指或触控笔等工具在屏幕上操作，也可以通过外接键盘，鼠标和轨迹球等工具操作
 * 非触屏设备。如TV，用户可以通过键盘鼠标或遥控器在屏幕上操作
 *
 * <h2>触屏事件<h2/>
 * 当用户用手指触摸手机屏幕的时候，会产生触屏事件MotionEvent。
 * 注意：虽说叫触屏事件，但是这个类是MotionEvent，在onTouchEvent方式中返回
 *
 * <h2>测试<h2/>
 * 手指接触屏幕 -> 滑动 -> 离开
 */
class TouchActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            // 手指接触屏幕时产生此事件，在多点触摸时，只有第一个手指接触屏幕时才会产生此事件。
            MotionEvent.ACTION_DOWN -> Log.d("TAG", "ACTION_DOWN")
            // 手指滑动屏幕时产生此事件， 在多点触摸时，每个手指的滑动都会产生一个此事件。
            MotionEvent.ACTION_MOVE -> Log.d("TAG", "ACTION_MOVE")
            // 手指离开屏幕时产生此事件，在多点触摸时，只有最后一个手指离开屏幕时才会产生此事件。
            MotionEvent.ACTION_UP -> Log.d("TAG", "ACTION_UP")
        }
        return super.onTouchEvent(event)
    }

}