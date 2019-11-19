package com.mackwu.component.db

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.component.R
import kotlinx.android.synthetic.main.activity_db.*

/**
 * ===================================================
 * Created by MackWu on 2019/11/1 19:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class DbActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)

        //
        btn_insert.setOnClickListener { StudentDao.rawInsert() }
        btn_query.setOnClickListener { StudentDao.query() }
    }

}