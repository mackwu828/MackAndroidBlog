package com.mackwu.http.other

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.http.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream

/**
 * ================================================
 * Created by MackWu on 2019/8/23 19:32
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * sax解析
 * sax解析是使用流式处理的方式。它并不记录所读内容的相关信息。以事件为驱动
 * 优点：解析速度快，占用内存少
 * 缺点：不像DOM解析一样将文档长期驻留在内存中，数据不是持久的。如果事件过后没有保存数据，数据就会丢失
 */
class SaxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener { parse(assets.open("user.xml")) }
    }

    private fun parse(inputStream: InputStream) {

    }

}