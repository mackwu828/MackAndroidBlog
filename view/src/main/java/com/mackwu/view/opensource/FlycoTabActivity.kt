package com.mackwu.view.opensource

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.view.R

/**
 * ===================================================
 * Created by MackWu on 2020/4/1 10:55
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class FlycoTabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.open_activity_flycotab)

//        val titles = arrayOf("home", "user")
//        val fragments: ArrayList<Fragment> = arrayListOf(HomeFragment(), UserFragment())
//
//        // view_pager
//        val adapter = CustomFragmentStatePagerAdapter(supportFragmentManager, fragments)
//        view_pager.adapter = adapter
//
//        // sliding_tab_layout
//        sliding_tab_layout.setViewPager(view_pager, titles)
    }
}