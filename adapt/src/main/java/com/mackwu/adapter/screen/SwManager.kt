package com.mackwu.adapter.screen

import java.io.File
import java.io.FileOutputStream
import java.math.BigDecimal

/**
 * ===================================================
 * Created by MackWu on 2019/9/29 15:41
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
object SwManager {

    // 设计图宽度或高度。取较小的那一个。填dp或者px都可以
//    const val DESIGN_WIDTH = 1080
    const val DESIGN_WIDTH = 540
    private const val ABSOLUTE_PATH = "C:\\Android\\workspace\\MackAndroidBlog\\adapt\\src\\main\\res"

    enum class SwType {
        SW_240,
        SW_280,
        SW_320,
        SW_360,
        SW_384,
        SW_392,
        SW_400,
        SW_410,
        SW_411,
        SW_432,
        SW_440,
        SW_480,
        SW_520,
        SW_533,
        SW_540,
        SW_560,
        SW_592,
        SW_600,
        SW_640,
        SW_662,
        SW_680,
        SW_720,
        SW_750,
        SW_760,
        SW_768,
        SW_800,
        SW_811,
        SW_820,
        SW_840,
        SW_880,
        SW_920,
        SW_960,
        SW_961,
        SW_1000,
        SW_1024,
        SW_1040,
        SW_1080,
        SW_1120,
        SW_1160,
        SW_1200,
        SW_1365,
        ;

        fun getSw(): Int {
            return name.replace("SW_", "").toInt()
        }
    }

    fun makeAllDimens() {
        makeAllDimens(DESIGN_WIDTH)
    }

    /**
     * 生成values-sw文件夹和dimens.xml文件
     * @param designWidth 设计图宽度或高度。取较小的那一个。填dp或者px都可以
     */
    fun makeAllDimens(designWidth: Int) {
        val swTypes = SwManager.SwType.values()
        for (swType in swTypes) {
            val sw = swType.getSw()
            // 生成文件夹。如values-sw300dp
            val file = File(ABSOLUTE_PATH + File.separator + "values-sw" + sw + "dp")
            if (!file.exists()) {
                file.mkdirs()
            }
            // 生成dimens.xml文件
            try {
                val fos = FileOutputStream(file.absolutePath + File.separator + "dimens.xml")
                val sb = StringBuilder()
                sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n")
                        .append("<resources>\r\n")
                        .append(String.format("   <string name=\"base_dpi\">%ddp</string>\r\n", sw))
                for (i in -50..1920) {
                    val dpValue = i / designWidth.toFloat() * sw
                    val bigDecimal = BigDecimal(dpValue.toDouble())
                    bigDecimal.setScale(4, BigDecimal.ROUND_HALF_UP).toFloat()
                    if (i < 0) {
                        sb.append(String.format("   <dimen name=\"_dp_%2\$d\">%3$.4fdp</dimen>\r\n", "", i * -1, dpValue))
                    } else {
                        sb.append(String.format("   <dimen name=\"dp_%2\$d\">%3$.4fdp</dimen>\r\n", "", i, dpValue))
                    }
                }
                for (i in 1..120) {
                    val spValue = i / designWidth.toFloat() * sw
                    val bigDecimal = BigDecimal(spValue.toDouble())
                    bigDecimal.setScale(4, BigDecimal.ROUND_HALF_UP).toFloat()
                    sb.append(String.format("   <dimen name=\"sp_%2\$d\">%3$.4fsp</dimen>\r\n", "", i, spValue))
                }
                sb.append("</resources>\r\n")
                fos.write(sb.toString().toByteArray())
                fos.flush()
                fos.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}

fun main() {
    SwManager.makeAllDimens()
}
