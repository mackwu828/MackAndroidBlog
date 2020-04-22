package com.mackwu.view.util

import androidx.appcompat.app.AppCompatActivity
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.include_smart_tab.*

/**
 * ===================================================
 * Created by MackWu on 2020/4/17 10:19
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * 初始化 smart tab layout
 */
fun AppCompatActivity.initSmartTab(creator: (FragmentPagerItems.Creator) -> Unit) {
    val adapter = FragmentPagerItemAdapter(
            supportFragmentManager, FragmentPagerItems.with(this)
            .apply { creator.invoke(this) }
            .create()
    )
    view_pager.adapter = adapter
    smart_tab_layout.setViewPager(view_pager)
}