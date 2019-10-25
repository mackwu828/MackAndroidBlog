package com.mackwu.view.custom

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import com.mackwu.view.R

/**
 * ================================================
 * Created by MackWu on 2019/8/27 15:43
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * 多点触屏
 */
class MultiTouchActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
//            // 手指接触屏幕时产生此事件，在多点触摸时，只有第一个手指接触屏幕时才会产生此事件。
//            MotionEvent.ACTION_DOWN -> Log.d("TAG", "ACTION_DOWN")
//            // 手指滑动屏幕时产生此事件， 在多点触摸时，每个手指的滑动都会产生一个此事件。
//            MotionEvent.ACTION_MOVE -> Log.d("TAG", "ACTION_MOVE")
//            // 手指离开屏幕时产生此事件，在多点触摸时，只有最后一个手指离开屏幕时才会产生此事件。
//            MotionEvent.ACTION_UP -> Log.d("TAG", "ACTION_UP")

            // 只有在多点触摸时才会产生此事件，在一个触屏事件序列中，除第一个接触屏幕的手指外，其他手指接触屏幕时会产生此事件。
            MotionEvent.ACTION_POINTER_DOWN -> Log.d("TAG", "ACTION_POINTER_DOWN")
            // 只有在多点触摸时才会产生此事件，在一个触屏事件序列中，除最后一个离开屏幕的手指外，其他手指离开屏幕时会产生此事件。
            MotionEvent.ACTION_POINTER_UP -> Log.d("TAG", "ACTION_POINTER_DOWN")
            // 当一个事件序列需要提前终止的时候由系统自动产生此事件
            MotionEvent.ACTION_CANCEL -> Log.d("TAG", "ACTION_POINTER_DOWN")
        }
        return super.onTouchEvent(event)
    }

}