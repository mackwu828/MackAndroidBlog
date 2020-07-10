package com.mackwu.view.util

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
 * set vertical style
 */
fun RecyclerView.setVerticalStyle(context: Context, adapter: RecyclerView.Adapter<*>) {
    layoutManager = LinearLayoutManager(context).apply { orientation = LinearLayoutManager.VERTICAL } // 布局管理器。线性布局，竖直方向
    setHasFixedSize(true)
    this.adapter = adapter
}

/**
 * set horizontal style
 */
fun RecyclerView.setHorizontalStyle(context: Context, adapter: RecyclerView.Adapter<*>) {
    layoutManager = LinearLayoutManager(context).apply { orientation = LinearLayoutManager.HORIZONTAL }
    setHasFixedSize(true)
    this.adapter = adapter
}

/**
 * set grid style
 */
fun RecyclerView.setGridStyle(context: Context, adapter: RecyclerView.Adapter<*>, spanCount: Int) {
    layoutManager = GridLayoutManager(context, spanCount).apply {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int = when (position) {
                0 -> 2
                1, 2 -> 1
                else -> 2
            }
        }
    }
    setHasFixedSize(true)
    this.adapter = adapter
}