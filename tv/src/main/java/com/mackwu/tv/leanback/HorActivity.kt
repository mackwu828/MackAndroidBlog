package com.mackwu.tv.leanback

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.tv.R

/**
 * ================================================
 * Created by MackWu on 2019/9/6 11:51
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * HorizontalGridView
 *
 * HorizontalGridView继承BaseGridView，而BaseGridView又继承RecyclerView，所以HorizontalGridView实际是RecyclerView的子类
 *
 * 标题栏、导航栏
 */
class HorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.leanback_activity_hor)

    }
}