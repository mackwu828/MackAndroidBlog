package com.mackwu.view.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mackwu.view.R
import kotlinx.android.synthetic.main.base_activity_vp.*

/**
 * ================================================
 * Created by MackWu on 2019/9/17 16:02
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class VpFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 数据
        val fragments = listOf(SimpleFragment(), SimpleFragment(), SimpleFragment())
        // 适配器
        val adapter = VpFragmentPagerAdapter(supportFragmentManager, fragments)
        view_pager.adapter = adapter
    }

    class SimpleFragment: Fragment(){
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.vp_fragment_simple, container, false)
        }
    }

    class VpFragmentPagerAdapter(fm: FragmentManager, private val fragments: List<Fragment>) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }
    }
}