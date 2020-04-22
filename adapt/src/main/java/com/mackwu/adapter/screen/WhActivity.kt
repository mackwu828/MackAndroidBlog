package com.mackwu.adapter.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.adapter.R

/**
 * ===================================================
 * Created by MackWu on 2019/9/20 16:19
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 *
 * 宽高限定符适配方案
 *
 * <h2>原理</h2>
 * 设定一个基准的分辨率，其他分辨率都根据这个基准分辨率来计算，在不同的尺寸文件夹内部，根据该尺寸编写对应的dimens文件
 *
 * <h2>例子</h2>
 * 以480x320为基准分辨率，新建dimens文件，设置x1=1px、x2=2px...
 * 宽度为320，将任何分辨率的宽度整分为320份，取值为x1-x320，高度为480，将任何分辨率的高度整分为480份，取值为y1-y480。
 * 那么对于800*480的分辨率的dimens文件来说：x1=(480/320)*1=1.5px、x2=(480/320)*2=3px...
 *
 * <h2>缺点</h2>
 * 1.需要精准命中才能适配，否则会按照高度向下兼容
 * 比如1920x1080的手机就一定要找到1920x1080的限定符，否则会向下兼容，如有1900x1080的限定符，则会使用该限定符下的文件
 *
 * 2.有两个兼容的维度(宽高)，导致适配文件会非常多
 */
class WhActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
    }
}