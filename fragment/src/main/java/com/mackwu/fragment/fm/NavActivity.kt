package com.mackwu.fragment.fm

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.mackwu.fragment.R
import com.mackwu.fragment.fm.fragment.HomeFragment
import com.mackwu.fragment.fm.fragment.PromoteFragment
import com.mackwu.fragment.fm.fragment.UserFragment
import kotlinx.android.synthetic.main.fm_activity_nav.*

/**
 * ===================================================
 * Created by MackWu on 2019/9/26 11:40
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class NavActivity : AppCompatActivity() {

    private var homeFragment: HomeFragment? = null
    private var promoteFragment: PromoteFragment? = null
    private var userFragment: UserFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fm_activity_nav)

        showFragment(0)
        tv_home.setOnClickListener { showFragment(0) }
        tv_promote.setOnClickListener { showFragment(1) }
        tv_user.setOnClickListener { showFragment(2) }
    }

    /**
     * show Fragment
     */
    private fun showFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideAllFragment(transaction)

        // 主页面
        when (position) {
            0 -> {
                if (null == homeFragment) {
                    homeFragment = HomeFragment()
                    transaction.add(R.id.fl_container, homeFragment!!, HomeFragment::class.java.simpleName)
                } else {
                    transaction.show(homeFragment!!)
                }
            }
            1 -> {
                if (null == promoteFragment) {
                    promoteFragment = PromoteFragment()
                    transaction.add(R.id.fl_container, promoteFragment!!, PromoteFragment::class.java.simpleName)
                } else {
                    transaction.show(promoteFragment!!)
                }
            }
            2 -> {
                if (null == userFragment) {
                    userFragment = UserFragment()
                    transaction.add(R.id.fl_container, userFragment!!, UserFragment::class.java.simpleName)
                } else {
                    transaction.show(userFragment!!)
                }
            }
        }
        transaction.commit()
    }

    /**
     * hide all fragment
     */
    private fun hideAllFragment(transaction: FragmentTransaction) {
        transaction.run {
            homeFragment?.run { hide(this) }
            userFragment?.run { hide(this) }
            promoteFragment?.run { hide(this) }
        }
    }

}