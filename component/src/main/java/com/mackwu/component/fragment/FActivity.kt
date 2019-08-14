package com.mackwu.component.fragment

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.mackwu.component.R
import com.mackwu.component.fragment.fm.FmActivity
import com.mackwu.component.fragment.tag.TagActivity
import kotlinx.android.synthetic.main.activity_f.*

class FActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_f)

        btn_tag.setOnClickListener { startActivity(Intent(this, TagActivity::class.java)) }
        btn_fm.setOnClickListener { startActivity(Intent(this, FmActivity::class.java)) }
    }

}
