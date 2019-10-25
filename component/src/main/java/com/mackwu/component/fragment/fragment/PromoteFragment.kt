package com.mackwu.component.fragment.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mackwu.component.R

/**
 * ===================================================
 * Created by MackWu on 2019/7/19 18:01
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class PromoteFragment : Fragment(){

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d("PromoteFragment", "onAttach...")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("PromoteFragment", "onCreate...")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("PromoteFragment", "onCreateView...")
        return inflater.inflate(R.layout.fragment_promote, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("PromoteFragment", "onActivityCreated...")
    }

    override fun onStart() {
        super.onStart()
        Log.d("PromoteFragment", "onStart...")
    }

    override fun onResume() {
        super.onResume()
        Log.d("PromoteFragment", "onResume...")
    }

    override fun onPause() {
        super.onPause()
        Log.d("PromoteFragment", "onPause...")
    }

    override fun onStop() {
        super.onStop()
        Log.d("PromoteFragment", "onStop...")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("PromoteFragment", "onDestroyView...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PromoteFragment", "onDestroy...")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("PromoteFragment", "onDetach...")
    }
    
}