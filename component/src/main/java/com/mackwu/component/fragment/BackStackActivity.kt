package com.mackwu.component.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.mackwu.component.R
import com.mackwu.component.fragment.fragment.HomeFragment
import com.mackwu.component.fragment.fragment.PromoteFragment
import com.mackwu.component.fragment.fragment.UserFragment
import kotlinx.android.synthetic.main.activity_fragment.*

/**
 * ===================================================
 * Created by MackWu on 2019/9/30 10:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * Fragment回退栈
 * Fragment默认是不添加到回退栈中的
 *
 * <h2>获取回退栈中的实例个数</h2>
 * supportFragmentManager.backStackEntryCount
 * @see getBackStackEntryCount()
 * 依次replace homeFragment、promoteFragment、userFragment，获取回退栈中的实例个数，0个
 * 09-29 23:03:51.137 3056-3056/com.mackwu.fragment D/TAG: backStackEntryCount: 0
 * 按返回键直接退出activity
 * 09-29 23:03:51.148 3056-3056/com.mackwu.fragment D/TAG: onDestroy...
 *
 * <h2>添加当前实例到回退栈</h2>
 * transaction.addToBackStack(null)
 * @see replaceFragmentAddToBackStack()
 * 获取回退栈中的实例个数，3个
 * 09-29 23:16:46.020 3180-3180/com.mackwu.fragment D/TAG: backStackEntryCount: 3
 * 按返回键，退到PromoteFragment
 * 09-30 03:40:36.565 6199-6199/com.mackwu.fragment D/PromoteFragment: onCreateView...
 * 09-30 03:40:36.567 6199-6199/com.mackwu.fragment D/PromoteFragment: onActivityCreated...
 * 09-30 03:40:36.567 6199-6199/com.mackwu.fragment D/PromoteFragment: onStart...
 * 09-30 03:40:36.567 6199-6199/com.mackwu.fragment D/PromoteFragment: onResume...
 *
 * <h2>移除回退栈中的一个实例</h2>
 * supportFragmentManager.popBackStack()，异步的。This function is asynchronous
 * @see popBackStack()
 * pop后获取回退栈中的实例个数还是3个
 * 09-30 03:43:08.245 6293-6293/com.mackwu.fragment D/TAG: backStackEntryCount: 3
 * 09-30 03:43:08.247 6293-6293/com.mackwu.fragment D/PromoteFragment: onCreateView...
 * 09-30 03:43:08.248 6293-6293/com.mackwu.fragment D/PromoteFragment: onActivityCreated...
 * 09-30 03:43:08.248 6293-6293/com.mackwu.fragment D/PromoteFragment: onStart...
 * 09-30 03:43:08.248 6293-6293/com.mackwu.fragment D/PromoteFragment: onResume...
 *
 * supportFragmentManager.popBackStackImmediate()。立即执行
 * @see popBackStackImmediate()
 * 09-30 03:46:23.382 6425-6425/com.mackwu.fragment D/PromoteFragment: onCreateView...
 * 09-30 03:46:23.383 6425-6425/com.mackwu.fragment D/PromoteFragment: onActivityCreated...
 * 09-30 03:46:23.383 6425-6425/com.mackwu.fragment D/PromoteFragment: onStart...
 * 09-30 03:46:23.383 6425-6425/com.mackwu.fragment D/PromoteFragment: onResume...
 * 09-30 03:46:23.383 6425-6425/com.mackwu.fragment D/TAG: backStackEntryCount: 2
 *
 *
 * <h2>移除回退栈中的所有实例</h2>
 * @see FragmentManager.popBackStack(String name, int flag)
 * name: 加入栈中的Fragment名字
 * flag：表示移走栈中Fragment的方式(0:把自己之前的弹出)(1:包括自己都弹出)
 * supportFragmentManager.popBackStack(null, 1)
 * @see popAll
 * 09-30 04:10:15.978 6648-6648/com.mackwu.fragment D/TAG: backStackEntryCount: 0
 *
 * @see FragmentManager.popBackStackImmediate(String name, int flag)
 * supportFragmentManager.popBackStackImmediate(null, 1)
 * @see popAll
 * 09-30 04:10:15.978 6648-6648/com.mackwu.fragment D/TAG: backStackEntryCount: 0
 *
 *
 * <h2>如果回退栈中已经没有实例了，则退出activity</h2>
 * if (supportFragmentManager.backStackEntryCount <= 0) finish()
 *
 */
class BackStackActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val promoteFragment = PromoteFragment()
    private val userFragment = UserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        replaceFragment(homeFragment)
        replaceFragment(promoteFragment)
        replaceFragment(userFragment)

        tv_home.setOnClickListener {
            popAll()
            getBackStackEntryCount()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy...")
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, fragment)
        transaction.commit()
    }

    private fun getBackStackEntryCount(){
        Log.d("TAG", "backStackEntryCount: ${supportFragmentManager.backStackEntryCount}")
    }

    private fun replaceFragmentAddToBackStack(fragment: androidx.fragment.app.Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, fragment)
        // 添加到回退栈
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun popBackStack(){
        supportFragmentManager.popBackStack()
    }

    private fun popBackStackImmediate(){
        supportFragmentManager.popBackStackImmediate()
    }

    private fun popAll(){
        supportFragmentManager.popBackStack(null, 1)
//        supportFragmentManager.popBackStackImmediate(null, 1)
        if (supportFragmentManager.backStackEntryCount <= 0) finish()
    }

}
