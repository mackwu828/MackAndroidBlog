package com.mackwu.view.vp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mackwu.view.R

/**
 * ================================================
 * Created by MackWu on 2019/9/18 10:46
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class HomeFragment : androidx.fragment.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.vp_fragment_home, container, false)
    }
}