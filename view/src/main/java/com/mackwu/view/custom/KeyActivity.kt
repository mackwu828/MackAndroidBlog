package com.mackwu.view.custom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.mackwu.view.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ================================================
 * Created by MackWu on 2019/8/27 15:55
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * 按键事件
 * 当用户通过键盘鼠标或遥控器在屏幕上操作时，会产生按键事件KeyEvent。
 *
 * <h2>测试<h2/>
 * 08-27 16:05:31.544 2858-2858/com.mackwu.viewcustom D/TAG: KEYCODE_DPAD_RIGHT
 * 08-27 16:05:32.545 2858-2858/com.mackwu.viewcustom D/TAG: KEYCODE_DPAD_LEFT
 * 08-27 16:05:33.366 2858-2858/com.mackwu.viewcustom D/TAG: KEYCODE_DPAD_UP
 * 08-27 16:05:34.004 2858-2858/com.mackwu.viewcustom D/TAG: KEYCODE_DPAD_DOWN
 * 08-27 16:06:03.506 2858-2858/com.mackwu.viewcustom D/TAG: KEYCODE_BACK
 */
class KeyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        // 按下
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (event.keyCode) {
                // 返回键
                KeyEvent.KEYCODE_BACK -> Log.d("TAG", "KEYCODE_BACK")
                // 上键
                KeyEvent.KEYCODE_DPAD_UP -> Log.d("TAG", "KEYCODE_DPAD_UP")
                // 下键
                KeyEvent.KEYCODE_DPAD_DOWN -> Log.d("TAG", "KEYCODE_DPAD_DOWN")
                // 左键
                KeyEvent.KEYCODE_DPAD_LEFT -> Log.d("TAG", "KEYCODE_DPAD_LEFT")
                // 右键
                KeyEvent.KEYCODE_DPAD_RIGHT -> Log.d("TAG", "KEYCODE_DPAD_RIGHT")
            }
        }
        return super.dispatchKeyEvent(event)
    }

}