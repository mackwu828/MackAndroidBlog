package com.mackwu.adapter.bean

/**
 * ===================================================
 * Created by MackWu on 2019/12/4 11:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
enum class Locale(val code: String, val language: Language){

    /**
     * 中文
     */
    ZH("zh", Language.ChineseSimplified),
    ZH_RCN("zh-rCN", Language.ChineseSimplified),
    ZH_RHK("zh-rHK", Language.ChineseTraditional),
    ZH_RTW("zh-rTW", Language.ChineseTraditional),

    /**
     * 英文
     */
    EN("en", Language.English),

    /**
     * 阿拉伯语
     */
    AR("ar", Language.Arabic),
    AR_REG("ar_rEG", Language.Arabic),
}