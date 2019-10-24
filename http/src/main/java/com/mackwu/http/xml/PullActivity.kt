package com.mackwu.http.xml

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.http.R

/**
 * ================================================
 * Created by MackWu on 2019/8/23 19:13
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * pull解析
 * pull解析内置于Android系统中，也是官方解析布局文件所使用的方式。
 * 优点：使用方便，效率高
 * 缺点：
 *
 * <h2>pull和sax的区别<h2/>
 * pull与sax有点类似，都提供了类似的事件，如开始元素和结束元素。
 * 不同的是，sax的事件驱动是回调相应方法，需要提供回调的方法，而后在SAX内部自动调用相应的方法。而pull解析器并没有强制要求提供触发的方法。因为他触发的事件不是一个方法，而是一个数字。
 */
class PullActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}