package com.mackwu.component

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.component.util.initSmartTab

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        initSmartTab { creator ->
            creator.add("activity", ActivityFragment::class.java)
            creator.add("basic", BasicFragment::class.java)
        }
    }

}