package com.mackwu.component.util.kt

import android.util.Base64
import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * ===================================================
 * Created by MackWu on 2019/10/28 15:41
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
fun md5(str: String): String {
    val digest = MessageDigest.getInstance("MD5")
    val result = digest.digest(str.toByteArray())
    return toHex(result)
}

fun sha1(str:String): String {
    val digest = MessageDigest.getInstance("SHA-1")
    val result = digest.digest(str.toByteArray())
    return toHex(result)
}

fun sha256(str:String): String {
    val digest = MessageDigest.getInstance("SHA-256")
    val result = digest.digest(str.toByteArray())
    return toHex(result)
}

fun hMacSha1(key: String, str:String): String{
    val secretKey = SecretKeySpec(key.toByteArray(), "HmacSHA1")
    val mac = Mac.getInstance("HmacSHA1")
    mac.init(secretKey)
    val result = mac.doFinal(str.toByteArray())
    return Base64.encodeToString(result, Base64.NO_WRAP)
}

fun toHex(byteArray: ByteArray): String {
    return with(StringBuilder()) {
        byteArray.forEach {
            val hex = it.toInt() and (0xFF)
            val hexStr = Integer.toHexString(hex)
            if (hexStr.length == 1) {
                append("0").append(hexStr)
            } else {
                append(hexStr)
            }
        }
        toString()
    }
}

fun main() {
    val text = "bar=1"
    println(md5(text))
}