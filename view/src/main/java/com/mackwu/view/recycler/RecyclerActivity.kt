package com.mackwu.view.recycler

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mackwu.view.R
import kotlinx.android.synthetic.main.recycler_activity.*

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
 */
class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_activity)

        // 布局管理器。设置为线性的布局
        val layoutManager = LinearLayoutManager(this)
        // 默认垂直
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        // adapter
        val adapter = RecyclerAdapter()
        // 分割线
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        // 动画
        val itemAnimator = DefaultItemAnimator()

        // recycler_view
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(dividerItemDecoration)
        recycler_view.itemAnimator = itemAnimator
    }

    class RecyclerAdapter : RecyclerView.Adapter<ViewHolder>() {

        private val list = listOf("Java", "Kotlin", "Android")

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvTest.text = list[position]
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTest = itemView.findViewById(R.id.tv_test) as TextView
    }
}