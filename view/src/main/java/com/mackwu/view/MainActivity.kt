package com.mackwu.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.view.util.initSmartTab
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSmartTab { creator ->
            creator.add("basic", BasicFragment::class.java)
            creator.add("opensource", OpenSourceFragment::class.java)
        }
    }
}
