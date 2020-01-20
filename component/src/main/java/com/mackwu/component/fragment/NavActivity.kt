package com.mackwu.component.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.component.R
import com.mackwu.component.fragment.fragment.HomeFragment
import com.mackwu.component.fragment.fragment.PromoteFragment
import com.mackwu.component.fragment.fragment.UserFragment
import kotlinx.android.synthetic.main.activity_fragment.*

/**
 * ===================================================
 * Created by MackWu on 2019/9/26 11:40
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * Fragment + 底部导航栏
 * App主页面一般都是设计成Fragment + 底部导航栏。
 *
 * <h2>底部导航栏1：LinearLayout + TextView + hide/show</h2>
 *
 * <h2>底部导航栏2：LinearLayout + RadioButton + hide/show</h2>
 */
class NavActivity : AppCompatActivity() {

    private var currentFragment = androidx.fragment.app.Fragment()
    private var homeFragment = HomeFragment()
    private var promoteFragment = PromoteFragment()
    private var userFragment = UserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        tv_home.setOnClickListener {
            setDefaultStyle()
            tv_home.isSelected = true
            showFragment(homeFragment)
        }
        tv_promote.setOnClickListener {
            setDefaultStyle()
            tv_promote.isSelected = true
            showFragment(promoteFragment)
        }
        tv_user.setOnClickListener {
            setDefaultStyle()
            tv_user.isSelected = true
            showFragment(userFragment)
        }
        tv_home.performClick()
    }

    /**
     * show Fragment
     */
    private fun showFragment(fragment: androidx.fragment.app.Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(currentFragment)
        currentFragment = fragment
        if (!fragment.isAdded) {
            transaction.add(R.id.fl_container, fragment)
        } else {
            transaction.show(fragment)
        }
        transaction.commit()
    }

    private fun setDefaultStyle() {
        tv_home.isSelected = false
        tv_promote.isSelected = false
        tv_user.isSelected = false
    }

}