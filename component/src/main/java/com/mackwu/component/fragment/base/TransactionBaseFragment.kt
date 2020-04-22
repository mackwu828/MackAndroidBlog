package com.mackwu.component.fragment.base

import android.os.Handler
import androidx.fragment.app.Fragment
import com.mackwu.component.R

/**
 * ===================================================
 * Created by MackWu on 2020/3/30 10:10
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
abstract class TransactionBaseFragment : SimpleBaseFragment(), FragmentTransaction {

    override fun replaceFragment(fragment: Fragment) {
        if (null == activity) return
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_container, fragment)
        transaction.commit()
    }

    override fun delayReplaceFragment(fragment: Fragment) {
        // 立即按返回键。java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        Handler().postDelayed({
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_container, fragment)
            transaction.commit()
//            transaction.commitAllowingStateLoss()
        }, 3000)
    }

    override fun showFragment(fragment: Fragment) {
        if (null == activity) return
        val transaction = activity!!.supportFragmentManager.beginTransaction()
//        transaction.hide(currentFragment)
//        currentFragment = fragment
        if (!fragment.isAdded) {
            transaction.add(R.id.fl_container, fragment)
        } else {
            transaction.show(fragment)
        }
        transaction.commit()
    }

}