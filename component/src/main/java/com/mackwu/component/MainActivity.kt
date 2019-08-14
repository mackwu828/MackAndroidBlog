package com.mackwu.component

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.component.fragment.fm.FmActivity
import com.mackwu.component.fragment.TagActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_tag.setOnClickListener { startActivity(Intent(this, TagActivity::class.java)) }
        btn_fm.setOnClickListener { startActivity(Intent(this, FmActivity::class.java)) }
    }

}
