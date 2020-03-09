package com.mackwu.http.jetpack.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mackwu.http.bean.User

/**
 * ===================================================
 * Created by MackWu on 2020/2/24 11:29
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class UserLiveData : MutableLiveData<User>(){

    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }
}