package com.mackwu.http

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mackwu.http.bean.User
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ===================================================
 * Created by MackWu on 2019/10/12 15:38
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * Gson
 * A Java serialization/deserialization library to convert Java Objects into JSON and back
 * @link https://github.com/google/gson
 *
 * <h2>初始化/h2>
 * 初始化1：默认初始化
 * @see init
 *
 * 初始化2：自定义
 * 格式化输出Json：setPrettyPrinting
 * 输出null：serializeNulls
 * @see initCustom
 *
 * <h2>序列化</h2>
 * @see toJson
 *
 * <h2>反序列化</h2>
 * @see fromJson
 *
 * <h2>属性重命名/h2>
 *  @SerializedName("user_name")
 *  @see User
 *
 * <h2>属性过滤/h2>
 * @Expose(serialize = true, deserialize = false)  //序列化时生效，反序列化时不生效
 *
 */
class GsonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener { }
    }

    private fun init(){
        val gson = Gson()
    }

    private fun initCustom(){
        val gson = GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create()
    }

    private fun toJson() {
        val user = User("1", "mack", "27")
        val gson = Gson()
        val json = gson.toJson(user)
        Log.d("TAG", json)
    }

    private fun fromJson() {
        val json = "{\"id\":\"1\",\"name\":\"mack\",\"age\":\"27\"}"
        val gson = Gson()
        val userBean = gson.fromJson(json, User::class.java)
        Log.d("TAG", userBean.toString())
    }

}