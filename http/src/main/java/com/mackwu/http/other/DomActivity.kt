package com.mackwu.http.other

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mackwu.http.R
import com.mackwu.http.bean.User
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory

/**
 * ================================================
 * Created by MackWu on 2019/8/23 19:31
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * dom解析
 * dom解析是把整个XML文档当成一个对象来处理，会先把整个文档读入到内存里，生成一个树结构。
 * 优点：1.由于整棵树在内存中，因此可以对xml文档随机访问 2.可以对xml文档进行修改操作c、较sax，dom使用也更简单。
 * 缺点：1.整个文档必须一次性解析完 2.由于整个文档都需要载入内存，对于大文档成本高
 */
class DomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener { parse(assets.open("user.xml")) }
    }

    private fun parse(inputStream: InputStream) {
        // documentBuilderFactory
        val documentBuilderFactory = DocumentBuilderFactory.newInstance()
        // documentBuilder
        val documentBuilder = documentBuilderFactory.newDocumentBuilder()
        // 获取文档对象
        val document = documentBuilder.parse(inputStream)
        // 解析
        parseDocument(document)
        // 关闭输入流
        inputStream.close()
    }

    private fun parseDocument(document: Document) {
        // 获取根节点
        val root = document.documentElement
        // 获取user节点列表
        val nodeList = root.getElementsByTagName("user")
        // 初始化
        val userList = arrayListOf<User>()
        for (i in 0 until nodeList.length) {
            // 获取单个user节点
            val element = nodeList.item(i) as Element
            val userBean = User()
            element.run {
                // 获取节点属性
                userBean.id = getAttribute("id")
                // 获取子节点
                childNodes.run {
                    for (j in 0 until length) {
                        when (item(j).nodeName) {
                            "name" -> userBean.name = item(j).firstChild.nodeValue
                            "age" -> userBean.age = item(j).firstChild.nodeValue
                        }
                    }
                }
            }
            userList.add(userBean)
        }
        Log.d("TAG", userList.toString())
    }

}