package com.mackwu.kt.pattern.proxy

/**
 * ===================================================
 * Created by MackWu on 2020/2/26 1:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
interface IGamePlayer {

    fun login(userName: String, password: String)

    fun killMonster()

    fun upgrade()
}