package com.mackwu.view.basic.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_recycler.*

/**
 * ===================================================
 * Created by MackWu on 2020/5/12 14:23
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), LayoutContainer {
    override val containerView: View = itemView
}