package com.mackwu.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mackwu.view.R
import kotlinx.android.synthetic.main.activity_recycler.*

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

        // 布局管理器。设置为线性的布局
        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        // 默认垂直
        layoutManager.orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
        // adapter
        val adapter = RecyclerAdapter()
        // 分割线
        val dividerItemDecoration = androidx.recyclerview.widget.DividerItemDecoration(this, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL)
        // 动画
        val itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()

        // recycler_view
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(dividerItemDecoration)
//        recycler_view.itemAnimator = itemAnimator
    }

    class RecyclerAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<ViewHolder>() {

        private val list = listOf("Java", "Kotlin", "Android")

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvTest.text = list[position]
        }

    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val tvTest = itemView.findViewById(R.id.tv_test) as TextView
    }
}