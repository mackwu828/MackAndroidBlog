package com.mackwu.http

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.http.jetpack.lifecycle.TestLifecycleActivity
import com.mackwu.http.jetpack.livedata.TestLiveDataActivity
import com.mackwu.http.jetpack.viewmodel.TestViewModelActivity
import com.mackwu.http.util.startActivityCls
import kotlinx.android.synthetic.main.activity_test.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // jetpack
        btn_lifecycle.setOnClickListener { startActivityCls(TestLifecycleActivity::class.java) }
        btn_live_data.setOnClickListener { startActivityCls(TestLiveDataActivity::class.java) }
        btn_view_model.setOnClickListener { startActivityCls(TestViewModelActivity::class.java) }
    }
}
