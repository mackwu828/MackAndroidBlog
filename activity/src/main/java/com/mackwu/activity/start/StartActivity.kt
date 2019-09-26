package com.mackwu.activity.start

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.activity.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ================================================
 * Created by MackWu on 2019/8/21 16:40
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * Activity的启动方式
 * 方式1：startActivity
 * 方式2：startActivityForResult
 *
 * <h2>startActivity</h2>
 * startActivity(Intent(this, TargetActivity::class.java))
 *
 * <h2>startActivityForResult</h2>
 * startActivityForResult(Intent(this, TargetActivity::class.java), REQUEST_CODE)
 * 请求码requestCode
 *
 * 响应码resultCode
 * setResult
 *
 *
 * <h2>启动Activity的最佳方式</h2>
 * @see TargetActivity.start(context: Context, param: String)
 * 在目标activity中定义一个静态方法，参数是context和要传入的参数
 */
class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        //startActivity
//        startActivity(Intent(this, TargetActivity::class.java))
//        // startActivityForResult
//        startActivityForResult(Intent(this, TargetActivity::class.java), REQUEST_CODE)
//        // 启动Activity的最佳方式
//        TargetActivity.start(this, "xxx")

        btn_test.setOnClickListener { startActivityForResult(Intent(this, TargetActivity::class.java), REQUEST_CODE) }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE -> {
                // todo
            }
        }
    }

    companion object {
        private const val REQUEST_CODE = 0x01
    }
}
