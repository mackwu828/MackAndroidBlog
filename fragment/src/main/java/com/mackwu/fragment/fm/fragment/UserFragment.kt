package com.mackwu.fragment.fm.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mackwu.fragment.R

/**
 * @author wmj
 * @date 2019/7/19 18:01
 */
class UserFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fm_fragment_user, container, false)
    }
    
}