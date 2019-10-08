package com.mackwu.adapter.screen.sw

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
//    const val DESIGN_WIDTH = 375
    const val DESIGN_WIDTH = 540
    private const val ABSOLUTE_PATH = "C:\\Android\\workspace\\MackAndroidBlog\\adapt\\src\\main\\res"

    private fun makeAllDimens() {
        makeAllDimens(DESIGN_WIDTH)
    }

    private fun makeAllDimens(designWidth: Int) {
        val swTypes = SwType.values()
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
                sb.append("<resources>\r\n")
                sb.append(String.format("   <string name=\"base_dpi\">%ddp</string>\r\n", sw))
                for (i in 1..720) {
                    val dpValue = i / designWidth.toFloat() * sw
                    val bigDecimal = BigDecimal(dpValue.toDouble())
                    bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toFloat()
                    sb.append(String.format("   <dimen name=\"dp_%2\$d\">%3$.2fdp</dimen>\r\n", "", i, dpValue))
                }
                for (i in 1..120) {
                    val spValue = i / designWidth.toFloat() * sw
                    val bigDecimal = BigDecimal(spValue.toDouble())
                    bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toFloat()
                    sb.append(String.format("   <dimen name=\"sp_%2\$d\">%3$.2fsp</dimen>\r\n", "", i, spValue))
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

    @JvmStatic
    fun main(args: Array<String>) {
        makeAllDimens()
    }

}