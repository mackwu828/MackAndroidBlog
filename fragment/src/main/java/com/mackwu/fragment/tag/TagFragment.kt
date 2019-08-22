package com.mackwu.fragment.tag

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mackwu.fragment.R

/**
 * ================================================
 * Created by MackWu on 2019/8/14 15:05
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * ================================================
 */
class TagFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tag_fragment, container, false)
    }
}