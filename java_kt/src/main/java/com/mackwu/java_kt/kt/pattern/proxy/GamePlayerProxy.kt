package com.mackwu.java_kt.kt.pattern.proxy

/**
 * ===================================================
 * Created by MackWu on 2020/2/26 1:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
// 代理类实现被代理类相同的接口
class GamePlayerProxy(name: String) : IGamePlayer {

    // 持有被代理对象
    private val gamePlayer: IGamePlayer = GamePlayer(name)

    override fun login(userName: String, password: String) {
        gamePlayer.login(userName, password)
    }

    override fun killMonster() {
        gamePlayer.killMonster()
    }

    override fun upgrade() {
        gamePlayer.upgrade()
    }
}

fun main() {
//    val gamePlayer = GamePlayer("张三")
//    val gamePlayerProxy = GamePlayerProxy(gamePlayer)

    val gamePlayerProxy = GamePlayerProxy("张三")
    gamePlayerProxy.login("赵日天", "123")
    gamePlayerProxy.killMonster()
    gamePlayerProxy.upgrade()
}