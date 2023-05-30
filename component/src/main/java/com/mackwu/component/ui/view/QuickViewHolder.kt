package com.mackwu.component.ui.view

import android.view.View
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author MackWu
 * @since 2022/11/8 16:14
 */
class QuickViewHolder(view: View) : BaseViewHolder(view) {

    val itemView: View
        get() = itemView

    val itemPosition: Int
        get() = adapterPosition
}