package com.mackwu.fragment.base

import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.preference.PreferenceFragment
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mackwu.fragment.R
import com.mackwu.fragment.fragment.HomeFragment
import com.mackwu.fragment.fragment.PromoteFragment
import kotlinx.android.synthetic.main.activity_fragment.*

/**
 * ================================================
 * Created by MackWu on 2019/8/14 14:49
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * ================================================
 * Fragment
 * Fragment可以简单理解为放在Activity中的控件。他拥有自己的生命周期，可以和用户交互，他的交互都是通过FragmentManager实现。
 * 继承Fragment，实现onCreateView
 *
 * <h2>生命周期</h2>
 * @see HomeFragment
 *
 * <h2>创建方式</h2>
 * 创建方式一：在xml中使用Fragment。使用fragment标签
 * 创建方式二：动态添加Fragment。在xml中创建一个布局如FrameLayout，然后通过FragmentManager进行显示
 * 1.supportFragmentManager.beginTransaction开启事务
 * 2.replace替换Fragment。或者add、hide、show添加、隐藏和显示Fragment
 * 3.transaction.commit()提交事务
 *
 * replace替换Fragment:
 * @see replaceFragment(fragment: Fragment)
 * replace时如果在layout中已经有Fragment存在，则该Fragment会先被销毁，每次replace都会创建新的Fragment实例
 *
 * replace HomeFragment
 * 09-30 02:30:46.641 4616-4616/com.mackwu.fragment D/HomeFragment: onAttach...
 * 09-30 02:30:46.641 4616-4616/com.mackwu.fragment D/HomeFragment: onCreate...
 * 09-30 02:30:46.641 4616-4616/com.mackwu.fragment D/HomeFragment: onCreateView...
 * 09-30 02:30:46.644 4616-4616/com.mackwu.fragment D/HomeFragment: onActivityCreated...
 * 09-30 02:30:46.644 4616-4616/com.mackwu.fragment D/HomeFragment: onStart...
 * 09-30 02:30:46.644 4616-4616/com.mackwu.fragment D/HomeFragment: onResume...
 *
 * replace PromoteFragment: 在PromoteFragment被创建后，HomeFragment会被销毁
 * 09-30 02:31:23.062 4616-4616/com.mackwu.fragment D/PromoteFragment: onAttach...
 * 09-30 02:31:23.062 4616-4616/com.mackwu.fragment D/PromoteFragment: onCreate...
 * 09-30 02:31:23.062 4616-4616/com.mackwu.fragment D/HomeFragment: onPause...
 * 09-30 02:31:23.062 4616-4616/com.mackwu.fragment D/HomeFragment: onStop...
 * 09-30 02:31:23.062 4616-4616/com.mackwu.fragment D/HomeFragment: onDestroyView...
 * 09-30 02:31:23.064 4616-4616/com.mackwu.fragment D/HomeFragment: onDestroy...
 * 09-30 02:31:23.064 4616-4616/com.mackwu.fragment D/HomeFragment: onDetach...
 * 09-30 02:31:23.064 4616-4616/com.mackwu.fragment D/PromoteFragment: onCreateView...
 * 09-30 02:31:23.067 4616-4616/com.mackwu.fragment D/PromoteFragment: onActivityCreated...
 * 09-30 02:31:23.067 4616-4616/com.mackwu.fragment D/PromoteFragment: onStart...
 * 09-30 02:31:23.067 4616-4616/com.mackwu.fragment D/PromoteFragment: onResume...
 *
 * add、hide、show:
 * @see showFragment(fragment: Fragment)
 * show HomeFragment
 * 09-30 03:04:34.159 5487-5487/com.mackwu.fragment D/HomeFragment: onAttach...
 * 09-30 03:04:34.159 5487-5487/com.mackwu.fragment D/HomeFragment: onCreate...
 * 09-30 03:04:34.159 5487-5487/com.mackwu.fragment D/HomeFragment: onCreateView...
 * 09-30 03:04:34.161 5487-5487/com.mackwu.fragment D/HomeFragment: onActivityCreated...
 * 09-30 03:04:34.161 5487-5487/com.mackwu.fragment D/HomeFragment: onStart...
 * 09-30 03:04:34.161 5487-5487/com.mackwu.fragment D/HomeFragment: onResume...
 *
 * show PromoteFragment
 * 09-30 03:05:11.928 5487-5487/com.mackwu.fragment D/PromoteFragment: onAttach...
 * 09-30 03:05:11.928 5487-5487/com.mackwu.fragment D/PromoteFragment: onCreate...
 * 09-30 03:05:11.928 5487-5487/com.mackwu.fragment D/PromoteFragment: onCreateView...
 * 09-30 03:05:11.930 5487-5487/com.mackwu.fragment D/PromoteFragment: onActivityCreated...
 * 09-30 03:05:11.930 5487-5487/com.mackwu.fragment D/PromoteFragment: onStart...
 * 09-30 03:05:11.930 5487-5487/com.mackwu.fragment D/PromoteFragment: onResume...
 *
 * 再show HomeFragment，没有走生命周期方法
 * 按返回键。
 * 09-30 03:06:03.310 5487-5487/com.mackwu.fragment D/HomeFragment: onPause...
 * 09-30 03:06:03.310 5487-5487/com.mackwu.fragment D/PromoteFragment: onPause...
 * 09-30 03:06:03.318 5487-5487/com.mackwu.fragment D/HomeFragment: onStop...
 * 09-30 03:06:03.318 5487-5487/com.mackwu.fragment D/PromoteFragment: onStop...
 * 09-30 03:06:03.318 5487-5487/com.mackwu.fragment D/HomeFragment: onDestroyView...
 * 09-30 03:06:03.322 5487-5487/com.mackwu.fragment D/HomeFragment: onDestroy...
 * 09-30 03:06:03.322 5487-5487/com.mackwu.fragment D/HomeFragment: onDetach...
 * 09-30 03:06:03.322 5487-5487/com.mackwu.fragment D/PromoteFragment: onDestroyView...
 * 09-30 03:06:03.325 5487-5487/com.mackwu.fragment D/PromoteFragment: onDestroy...
 * 09-30 03:06:03.325 5487-5487/com.mackwu.fragment D/PromoteFragment: onDetach...
 *
 * <h2>Fragment + 底部导航栏</h2>
 * @see NavActivity
 *
 * <h2>回退栈</h2>
 * @see BackStackActivity
 *
 * <h2>commit和commitAllowingStateLoss的区别</h2>
 * Fragment的控制采用事务方式执行，在事务执行之前，用户可能已经点击了返回键，导致Activity的onSaveInstanceState()方法被调用，在保存当前activity实例的状态以备恢复
 * 在onSaveInstanceState()方法中会将一个成员变量mStateSaved设为true。
 * @see onSaveInstanceState
 * @see android.support.v4.app.FragmentManagerImpl.saveAllState
 *
 * 采用commit()方式提交事务，在执行时会检查mStateSaved的值，若为true，则会抛出异常。
 * 因此事务必须保证在用户点击返回键之前执行。而采用commitAllowingStateLoss()方式提交事务，在执行时不会检查mStateSaved的值，不会发生异常。
 * @see android.support.v4.app.FragmentManagerImpl.checkStateLoss
 *
 * 模拟: 延迟commit()方式提交事务，按返回键，出现异常
 * java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
 *
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

    private var currentFragment = Fragment()
    private var homeFragment = HomeFragment()
    private var promoteFragment = PromoteFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        tv_home.setOnClickListener { replaceFragment(homeFragment) }
        tv_promote.setOnClickListener { replaceFragment(promoteFragment) }

        //        tv_home.setOnClickListener { delayReplaceFragment(homeFragment) }
    }

    /**
     * replace Fragment
     */
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, fragment)
        transaction.commit()
    }

    /**
     * show Fragment
     */
    private fun showFragment(fragment: Fragment) {
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


    private fun delayReplaceFragment(fragment: Fragment) {
        // 立即按返回键。java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        Handler().postDelayed({
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_container, fragment)
            transaction.commit()
//            transaction.commitAllowingStateLoss()
        }, 3000)
    }

}
