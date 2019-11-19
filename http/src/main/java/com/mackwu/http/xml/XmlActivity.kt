package com.mackwu.http.xml

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.http.R

/**
 * ===================================================
 * Created by MackWu on 2019/10/12 15:45
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * Xml
 *
 * <h2>Xml的3种解析方式</h2>
 * 解析方式1：dom解析
 * @see DomActivity
 * 解析方式2：sax解析
 * @see SaxActivity
 * 解析方式3：pull解析
 * @see PullActivity
 */
class XmlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}