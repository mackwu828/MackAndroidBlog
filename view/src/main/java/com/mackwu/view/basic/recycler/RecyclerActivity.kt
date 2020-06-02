package com.mackwu.view.basic.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.view.R
import com.mackwu.view.util.initGridRecyclerView
import com.mackwu.view.util.initVerticalRecyclerView

/**
 * ================================================
 * Created by MackWu on 2019/9/6 13:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * RecyclerView
 *
 * <h2>布局管理器</h2>
 * 使用时需要先设置布局管理器
 * LinearLayoutManager
 * GridLayoutManager
 *
 * <h2>适配器</h2>
 *
 * <h2>分割线</h2>
 *
 * <h2>动画</h2>
 *
 * <h2>滚动条</h2>
 * 滚动条方向：android:scrollbars="vertical"
 * 滚动条宽度：android:scrollbarSize="5dp"
 * 滚动条颜色：android:scrollbarThumbVertical="@color/white"
 *
 */
class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        // adapter
        val list = listOf("Java", "Kotlin", "Android", "Python")
        val adapter = RecyclerAdapter(list)
        // RecyclerView
        initGridRecyclerView(adapter, 2)
    }

}