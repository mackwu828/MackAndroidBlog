package com.mackwu.adapter.productflavor

import java.io.File
import java.io.FileOutputStream

/**
 * ===================================================
 * Created by MackWu on 2019/11/19 15:25
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 1. 如何获取Gradle文件中productFlavors的值？
 * 2. 如何插件化？
 */
object ProductFlavorManager {

    private val modulePath: String
        get() {
            val rootPath = File("").canonicalPath
            val currentPath = File(this::class.java.getResource("")?.path).toString()
            val moduleName = currentPath.replace(rootPath, "").split("\\")[1]
            val modulePath = rootPath + File.separator + moduleName + File.separator + "src"
            println("""
            rootPath: $rootPath
            currentPath: $currentPath
            moduleName: $moduleName
            modulePath: $modulePath
        """.trimIndent()
            )
            return modulePath
        }


    fun makeAllProductFlavors() {
        val productFlavors = ProductFlavor.values()
        for (productFlavor in productFlavors) {
            productFlavor.run {
                val productFlavorPath = modulePath + File.separator + name + File.separator + "res" + File.separator + "values"
                val file = File(productFlavorPath)
                if (!file.exists()) {
                    file.mkdirs()
                }
                try {
                    val fos = FileOutputStream(file.absolutePath + File.separator + "product_flavors.xml")
                    val sb = StringBuilder()
                    sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n")
                            .append("<resources>\r\n")
                            .append(String.format("   <string name=\"product_id_dev\">%d</string>\r\n", productId.dev))
                            .append(String.format("   <string name=\"product_id_acc\">%d</string>\r\n", productId.acc))
                            .append(String.format("   <string name=\"product_id_pro\">%d</string>\r\n", productId.pro))
                            .append(String.format("   <string name=\"channel_id_dev\">%d</string>\r\n", channelId.dev))
                            .append(String.format("   <string name=\"channel_id_acc\">%d</string>\r\n", channelId.acc))
                            .append(String.format("   <string name=\"channel_id_pro\">%d</string>\r\n", channelId.pro))
                            .append(String.format("   <string name=\"brand_id_dev\">%d</string>\r\n", brandId.dev))
                            .append(String.format("   <string name=\"brand_id_acc\">%d</string>\r\n", brandId.acc))
                            .append(String.format("   <string name=\"brand_id_pro\">%d</string>\r\n", brandId.pro))
                            .apply { if (deviceType.isNotEmpty()) sb.append(String.format("   <string name=\"device_type\">%s</string>\r\n", deviceType)) }
                            .append(String.format("   <string name=\"access_key_dev\">%s</string>\r\n", accessKey.dev))
                            .append(String.format("   <string name=\"access_key_acc\">%s</string>\r\n", accessKey.acc))
                            .append(String.format("   <string name=\"access_key_pro\">%s</string>\r\n", accessKey.pro))
                            .append(String.format("   <string name=\"access_secret_key_dev\">%s</string>\r\n", accessSecretKey.dev))
                            .append(String.format("   <string name=\"access_secret_key_acc\">%s</string>\r\n", accessSecretKey.acc))
                            .append(String.format("   <string name=\"access_secret_key_pro\">%s</string>\r\n", accessSecretKey.pro))
                            .append("</resources>\r\n")
                    fos.write(sb.toString().toByteArray())
                    fos.flush()
                    fos.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }
    }

}

fun main() {
    ProductFlavorManager.makeAllProductFlavors()
}