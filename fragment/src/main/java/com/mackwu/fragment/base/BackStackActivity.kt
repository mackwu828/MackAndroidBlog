package com.mackwu.fragment.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mackwu.fragment.R
import com.mackwu.fragment.fragment.HomeFragment
import com.mackwu.fragment.fragment.PromoteFragment
import com.mackwu.fragment.fragment.UserFragment
import kotlinx.android.synthetic.main.activity_fragment.*

/**
 * ===================================================
 * Created by MackWu on 2019/9/30 10:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * Fragment回退栈
 * 默认是不添加到回退栈中的
 *
 * <h2>获取回退栈中的实例个数</h2>
 * supportFragmentManager.backStackEntryCount
 *
 * <h2>添加当前实例到回退栈</h2>
 * transaction.addToBackStack(null)
 *
 * <h2>移除回退栈中的一个实例</h2>
 * supportFragmentManager.popBackStack()
 *
 * <h2>移除回退栈中的所有实例</h2>
 * supportFragmentManager.popBackStackImmediate(null, 1)
 *
 * <h2>测试</h2>
 * 依次replace homeFragment、promoteFragment、userFragment
 *
 * 默认情况，按返回键回退栈中无fragment实例，直接退出activity
 * 09-29 23:03:51.137 3056-3056/com.mackwu.fragment D/TAG: backStackEntryCount: 0
 * 09-29 23:03:51.148 3056-3056/com.mackwu.fragment D/TAG: onDestroy...
 *
 * replace时添加当前实例到回退栈，按返回键1次回退栈中还有2个fragment
 * 09-29 23:16:46.020 3180-3180/com.mackwu.fragment D/TAG: backStackEntryCount: 2
 * 再按1次返回键，回退栈中还有1个fragment
 * 09-29 23:16:59.202 3180-3180/com.mackwu.fragment D/TAG: backStackEntryCount: 1
 * 再按1次返回键，回退栈中无fragment实例，退出activity
 * 09-29 23:17:45.666 3180-3180/com.mackwu.fragment D/TAG: backStackEntryCount: 0
 * 09-29 23:17:45.707 3180-3180/com.mackwu.fragment D/TAG: onDestroy...
 *
 * replace后移除回退栈中的一个实例，按返回键1次回退栈中还有1个fragment
 * 09-29 23:19:17.488 3265-3265/com.mackwu.fragment D/TAG: backStackEntryCount: 1
 *
 * 移除回退栈中的所有实例
 * 2019-09-26 14:56:27.375 27199-27199/com.mackwu.fragment D/TAG: backStackEntryCount: 0
 */
class BackStackActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val promoteFragment = PromoteFragment()
    private val userFragment = UserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        btn_test.setOnClickListener {
            replaceFragment(homeFragment)
            replaceFragment(promoteFragment)
            replaceFragment(userFragment)
//            supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy...")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("TAG", "backStackEntryCount: ${supportFragmentManager.backStackEntryCount}")
        // 如果回退栈中已经没有fragment实例了，则退出activity
        if (supportFragmentManager.backStackEntryCount <= 0) finish()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
