package com.mackwu.component

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.component.util.initSmartTab

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSmartTab { creator ->
            creator.add("activity", ActivityFragment::class.java)
            creator.add("jetpack", JetpackFragment::class.java)
            creator.add("other", OtherFragment::class.java)
        }
    }

}