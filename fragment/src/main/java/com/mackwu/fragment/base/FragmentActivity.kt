package com.mackwu.fragment.base

import android.os.Bundle
import android.preference.PreferenceFragment
import android.support.v4.app.DialogFragment
import android.support.v4.app.ListFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.mackwu.fragment.R
import com.mackwu.fragment.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_fragment.*

/**
 * ================================================
 * Created by MackWu on 2019/8/14 14:49
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * ================================================
 * Fragment
 * Fragment可以简单理解为放在Activity中的控件。他拥有自己的生命周期，可以和用户交互，他的交互都是通过FragmentManager实现。
 *
 * <h2>创建方式</h2>
 * 创建方式一：在xml中使用Fragment。使用fragment标签
 * 创建方式二：动态添加Fragment。在xml中创建一个布局如FrameLayout，然后通过FragmentManager进行显示
 * hide和show
 * replace和remove
 *
 * <h2>回退栈</h2>
 * @see BackStackActivity
 *
 * <h2>Fragment + 底部导航栏</h2>
 * App主页面一般都是设计成Fragment + 底部导航栏。
 * @see NavActivity
 * 底部导航栏1：LinearLayout + TextView + hide/show
 * 底部导航栏2：LinearLayout + RadioButton + hide/show
 *
 * <h2>Fragment嵌套</h2>
 * <h2>Fragment重叠</h2>
 * <h2>Fragment和Activity的通信</h2>
 * <h2>Fragment保存上次的页面</h2>
 *
 * <h2>系统的Fragment</h2>
 * @see DialogFragment
 * @see ListFragment
 * @see PreferenceFragment
 */
class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)



        btn_test.setOnClickListener { replaceFragment(LifecycleFragment()) }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, fragment)
        transaction.commit()
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_container, HomeFragment())
        transaction.commit()
    }

}