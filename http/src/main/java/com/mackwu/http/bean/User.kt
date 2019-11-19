package com.mackwu.http.bean

import com.google.gson.annotations.SerializedName

/**
 * ================================================
 * Created by MackWu on 2019/8/23 19:30
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
data class User (
        var id: String = "",

        @SerializedName("user_name")
        var name: String = "",
        var age: String = ""
)