package com.mackwu.view.basic.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mackwu.view.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_recycler.*

/**
 * ===================================================
 * Created by MackWu on 2020/5/9 16:31
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class RecyclerAdapter(val list: List<String>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.tvText.text = list[position]
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvText: TextView = itemView.findViewById(R.id.tv_test)
    }
}