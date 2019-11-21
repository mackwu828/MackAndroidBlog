package com.mackwu.http

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.http.R

/**
 * ===================================================
 * Created by MackWu on 2019/10/12 15:37
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * Json
 * Json是一种文本形式的数据交换格式，比xml更为轻量
 *
 * <h2>Json语法</h2>
 * 数组（Array）用方括号(“[]”)表示。
 * 对象（Object）用大括号（”{}”）表示。
 * 名称/值对（name/value）组合成数组和对象。
 * 名称（name）置于双引号中，值（value）有字符串、数值、布尔值、null、对象和数组。
 * 并列的数据之间用逗号（“,”）分隔
 *
 * Json例子：
 * {
 *   name: "mack",
 *   age: 26
 * }
 *
 * <h2>序列化和反序列化</h2>
 * 序列化：把对象转换为字节序列的过程称为对象的序列化。
 * 反序列化：把字节序列恢复为对象的过程称为对象的反序列化。
 *
 * <h2>Json生成</h2>
 *
 * <h2>Json解析</h2>
 *
 * <h2>Json解析库</h2>
 * json解析库1：Gson
 * @see GsonActivity
 * json解析库2：FastJson
 * @see
 */
class JsonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}