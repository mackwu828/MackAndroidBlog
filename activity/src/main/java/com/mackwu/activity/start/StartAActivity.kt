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
 */
class StartAActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE = 0x01
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener { startActivity() }
//        btn_test.setOnClickListener { startActivityForResult() }
//        btn_test.setOnClickListener { StartBActivity.start(this, "xxx") }
    }

    /**
     * startActivity
     */
    private fun startActivity() {
        val intent = Intent(this, StartBActivity::class.java)
        startActivity(intent)
    }

    /**
     * startActivityForResult
     */
    private fun startActivityForResult() {
        val intent = Intent(this, StartBActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE -> {
                // todo
            }
        }
    }

}
