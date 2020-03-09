package com.mackwu.http.jetpack.livedata

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.mackwu.http.R
import com.mackwu.http.SecondActivity
import com.mackwu.http.bean.User
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ===================================================
 * Created by MackWu on 2020/2/24 10:56
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class TestLiveDataActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userLiveData = UserLiveData()
        userLiveData.observe(this, Observer { user -> btn_test.text = user.name })

        // 转换 LiveData
        val userName = Transformations.map(userLiveData) { user -> user.name }
        userName.observe(this, Observer {  })

        Transformations.switchMap(userLiveData){ user -> MutableLiveData<String>() }


        btn_test.setOnClickListener { userLiveData.value = User("", "xxx", "") }
    }
}