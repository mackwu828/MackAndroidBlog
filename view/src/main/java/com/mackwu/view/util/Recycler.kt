package com.mackwu.view.util

import android.app.Activity
import androidx.recyclerview.widget.*
import kotlinx.android.synthetic.main.activity_recycler.*

/**
 * ===================================================
 * Created by MackWu on 2020/5/9 16:12
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 *
//    // 分割线
//    recycler_view.addItemDecoration(DividerItemDecoration(this@initRecyclerView, DividerItemDecoration.VERTICAL))
//    // 动画
//    recycler_view.itemAnimator = DefaultItemAnimator()
 */

/**
 * init vertical recycler view
 */
fun Activity.initVerticalRecyclerView(adapter: RecyclerView.Adapter<*>) {
    recycler_view.layoutManager = LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL } // 布局管理器。线性布局，竖直方向
    recycler_view.setHasFixedSize(true)
    recycler_view.adapter = adapter
}

/**
 * init horizontal recycler view
 */
fun Activity.initHorizontalRecyclerView(adapter: RecyclerView.Adapter<*>) {
    recycler_view.layoutManager = LinearLayoutManager(this).apply { orientation = LinearLayoutManager.HORIZONTAL }
    recycler_view.setHasFixedSize(true)
    recycler_view.adapter = adapter
}

/**
 * init
 */
fun Activity.initGridRecyclerView(adapter: RecyclerView.Adapter<*>, spanCount: Int) {
    recycler_view.layoutManager = GridLayoutManager(this, spanCount).apply {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int = when (position) {
                0 -> 2
                1, 2 -> 1
                else -> 2
            }
        }
    }
    recycler_view.setHasFixedSize(true)
    recycler_view.adapter = adapter
}