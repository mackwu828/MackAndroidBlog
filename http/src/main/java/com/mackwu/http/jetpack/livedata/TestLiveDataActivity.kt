package com.mackwu.http.jetpack.livedata

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.mackwu.http.R
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

    private val liveData = object : MutableLiveData<String>() {
        override fun onActive() {
            super.onActive()
            Log.d("TAG", "onActive...")
        }

        override fun onInactive() {
            super.onInactive()
            Log.d("TAG", "onInactive...")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // MutableLiveData
        val userLiveData = MutableLiveData<User>()
        // Observer
        val userObserver = Observer<User> { user -> btn_test.text = user.name }
        // observe
        userLiveData.observe(this, userObserver)

        // Transformations#map()
        val userNameLiveData = Transformations.map(userLiveData) { user -> user.name }
        val userNameObserver = Observer<String> { userName -> btn_test.text = userName }
        userNameLiveData.observe(this, userNameObserver)

        // MediatorLiveData
        val mediatorLiveData = MediatorLiveData<User>()
        val changeObserver = Observer<User> { value -> btn_test.text = value.name }
        mediatorLiveData.addSource(userLiveData){ userLiveData.value = User("", "a", "")}
        mediatorLiveData.addSource(userLiveData){ userLiveData.value = User("", "b", "")}
        mediatorLiveData.observe(this, changeObserver)

        // Transformations#switchMap()
        Transformations.switchMap(userLiveData) { user -> MutableLiveData<String>() }

        // 改变数据
        btn_test.setOnClickListener { userLiveData.value = User("", "xxx", "") }
    }
}