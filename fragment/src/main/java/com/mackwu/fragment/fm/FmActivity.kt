package com.mackwu.fragment.fm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mackwu.fragment.R
import com.mackwu.fragment.fm.fragment.HomeFragment
import com.mackwu.fragment.fm.fragment.PromoteFragment
import com.mackwu.fragment.fm.fragment.UserFragment
import kotlinx.android.synthetic.main.fm_activity.*

/**
 * ================================================
 * Created by MackWu on 2019/8/14 14:49
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * ================================================
 * Fragment
 * Fragment可以简单理解为放在Activity中的控件。他拥有自己的生命周期，可以和用户交互，他的交互都是通过FragmentManager实现。
 *
 * <h2>创建方式</h2>
 * 方式一：在xml中使用<fragment>标签
 * 方式二：在xml中创建一个布局如FrameLayout，然后通过FragmentManager进行显示
 *
 *
 * <h2>add、hide和show</h2>
 *
 * <h2>replace、remove</h2>
 *
 * <h2>回退栈</h2>
 * 默认是不添加到回退栈中的
 * 回退栈中fragment个数：supportFragmentManager.backStackEntryCount
 * 测试：
 * 依次replace homeFragment、promoteFragment、userFragment，按返回键，结果直接退出。
 * 2019-09-26 14:28:47.618 24581-24581/com.mackwu.fragment D/TAG: backStackEntryCount: 0
 *
 * 添加当前实例到回退栈：transaction.addToBackStack(null)
 * 2019-09-26 14:39:51.880 25610-25610/com.mackwu.fragment D/TAG: backStackEntryCount: 2
 * 2019-09-26 14:39:53.201 25610-25610/com.mackwu.fragment D/TAG: backStackEntryCount: 1
 * 2019-09-26 14:39:53.980 25610-25610/com.mackwu.fragment D/TAG: backStackEntryCount: 0
 *
 * 移除回退栈中的一个实例：supportFragmentManager.popBackStack()
 * 2019-09-26 14:46:01.976 26268-26268/com.mackwu.fragment D/TAG: backStackEntryCount: 2
 * 2019-09-26 14:46:03.064 26268-26268/com.mackwu.fragment D/TAG: backStackEntryCount: 0
 *
 * 移除回退栈中的所有实例：supportFragmentManager.popBackStackImmediate(null, 1)
 * 2019-09-26 14:56:27.375 27199-27199/com.mackwu.fragment D/TAG: backStackEntryCount: 0
 *
 * <h2>Fragment + 底部导航栏</h2>
 * 主页面一般都是设计成Fragment + 底部导航栏。
 * @see NavActivity
 * 底部导航栏1：LinearLayout + TextView + hide/show
 * 底部导航栏2：LinearLayout + RadioButton + hide/show
 */
class FmActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fm_activity)
        val homeFragment = HomeFragment()
        val promoteFragment = PromoteFragment()
        val userFragment = UserFragment()

        btn_test.setOnClickListener {
            replaceFragment(homeFragment)
            replaceFragment(promoteFragment)
            replaceFragment(userFragment)
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // 如果回退栈中已经没有fragment实例了，则退出activity
        supportFragmentManager.popBackStackImmediate(null, 1)
        Log.d("TAG", "backStackEntryCount: ${supportFragmentManager.backStackEntryCount}")
        if (supportFragmentManager.backStackEntryCount <= 0) finish()
    }

}