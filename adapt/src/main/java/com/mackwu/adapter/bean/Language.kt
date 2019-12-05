package com.mackwu.adapter.bean

/**
 * ===================================================
 * Created by MackWu on 2019/12/4 11:10
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
enum class Language(val code: String, val chineseName: String, englishName: String) {

    /**
     * 中文
     */
    ChineseSimplified("zh-CN","简体中文", "Chinese Simplified"),
    ChineseTraditional("zh-CN", "繁体中文", "Chinese Traditional"),

    /**
     * 英文
     */
    English("en", "英文", "English"),

    /**
     * 阿拉伯语
     */
    Arabic("ar", "阿拉伯语", "Arabic"),
}