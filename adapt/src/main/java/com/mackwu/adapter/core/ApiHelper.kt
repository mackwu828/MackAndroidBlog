package com.mackwu.adapter.core

import android.util.Log

/**
 * ===================================================
 * Created by MackWu on 2019/12/4 14:46
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
object ApiHelper {

    /**
     * @param client gtx
     * @param dt 决定了最终返回的数据。t: 源text的翻译、at: 会额外返回一些近义词
     * @param sl auto: 自动检测、 en: 英文
     * @param tl zh-CN: 中文
     * @param q 源text
     */
    fun translate() {
        val url = "http://translate.google.cn/translate_a/single"
        val param = hashMapOf<String, String>()
        param["client"] = "gtx"
        param["dt"] = "t"
        param["sl"] = "auto"
        param["tl"] = "en"
        param["q"] = "热门电视点播"
        OkHttpManager.get(url, param) {
//            Log.d("TAG", it)
            val results = it.split("\"")
            Log.d("TAG", results[1])
        }
    }
    
}