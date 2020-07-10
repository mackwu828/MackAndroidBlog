package com.mackwu.java_kt.kt.pattern.proxy

/**
 * ===================================================
 * Created by MackWu on 2020/2/26 1:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class GamePlayer(val name: String) : IGamePlayer {

    override fun login(userName: String, password: String) {
        println("登录名为 $userName 的用户 $name 登录成功！")
    }

    override fun killMonster() {
    }

    override fun upgrade() {
    }

}