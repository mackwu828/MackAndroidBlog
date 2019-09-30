package com.mackwu.adapter.screen.sw

/**
 * ===================================================
 * Created by MackWu on 2019/9/29 15:50
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 大部分手机的sw值集中在300-460之间
 */
enum class SwType {
    SW_300,
    SW_310,
    SW_320,
    SW_330,
    SW_340,
    SW_350,
    SW_360,
    SW_370,
    SW_380,
    SW_390,
    SW_400,
    SW_410,
    SW_420,
    SW_430,
    SW_440,
    SW_450,
    SW_460,
    SW_470,
    SW_480,
    SW_490,
    SW_500,
    SW_510,
    SW_520,
    SW_530,
    SW_540,
    SW_550,
    SW_560;

    fun getSw(): Int {
        return name.replace("SW_", "").toInt()
    }
}