package com.mackwu.http.jetpack.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mackwu.http.bean.Ip
import com.mackwu.http.okhttp_retrofit_rxjava.ApiHelper

/**
 * ===================================================
 * Created by MackWu on 2019/12/23 19:03
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class IpViewModel : ViewModel() {

    val ipLiveData = MutableLiveData<Ip>()

    fun getIp() {
        ApiHelper.getIp { ip -> ipLiveData.value = ip }
    }
}