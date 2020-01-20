package com.mackwu.view.vp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.view.R
import com.mackwu.view.vp.fragment.HomeFragment
import com.mackwu.view.vp.fragment.PromoteFragment
import com.mackwu.view.vp.fragment.UserFragment
import kotlinx.android.synthetic.main.vp_activity_f_tab.*

/**
 * ================================================
 * Created by MackWu on 2019/9/17 16:02
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class VpFTabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vp_activity_f_tab)

        // view_pager
        val fragments = listOf(HomeFragment(), PromoteFragment(), UserFragment())
        val adapter = VpFragmentPagerAdapter(supportFragmentManager, fragments)
        view_pager.adapter = adapter
        view_pager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(position: Int) {
                view_pager.setCurrentItem(position, true)
                // 样式
                setDefaultStyle()
                when (position) {
                    0 -> tv_home.setBackgroundColor(Color.BLUE)
                    1 -> tv_promote.setBackgroundColor(Color.BLUE)
                    2 -> tv_user.setBackgroundColor(Color.BLUE)
                }
            }

        })
        tv_home.setBackgroundColor(Color.BLUE)

        //
        tv_home.setOnClickListener { view_pager.setCurrentItem(0, true) }
        tv_promote.setOnClickListener { view_pager.setCurrentItem(1, true) }
        tv_user.setOnClickListener { view_pager.setCurrentItem(2, true) }
    }

    private fun setDefaultStyle(){
        tv_home.setBackgroundColor(Color.WHITE)
        tv_promote.setBackgroundColor(Color.WHITE)
        tv_user.setBackgroundColor(Color.WHITE)
    }

    class VpFragmentPagerAdapter(fm: androidx.fragment.app.FragmentManager, private val fragments: List<androidx.fragment.app.Fragment>) : androidx.fragment.app.FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): androidx.fragment.app.Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }
    }
}