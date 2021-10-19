package com.mackwu.component.util;

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * ===================================================
 * Created by MackWu on 2020/6/29 14:00
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class EncryptUtil {

    /**
     * 加密
     *
     * @param algorithm 算法
     * @param str       需要加密的字符串
     */
    public static String encrypt(String algorithm, String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] result = digest.digest(str.getBytes());
            return ByteUtil.bytesToHex(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 加密
     *
     * @param algorithm 算法
     * @param key       key
     * @param str       需要加密的字符串
     */
    public static String encrypt(String algorithm, String key, String str) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(secretKey);
            byte[] result = mac.doFinal(str.getBytes());
            return Base64.encodeToString(result, Base64.NO_WRAP);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * MD5加密
     */
    public static String md5(String str) {
        return encrypt("MD5", str);
    }

    /**
     * SHA-1加密
     */
    public static String sha1(String str) {
        return encrypt("SHA-1", str);
    }

    /**
     * SHA-256加密
     */
    public static String sha256(String str) {
        return encrypt("SHA-256", str);
    }

    /**
     * HmacSHA1加密
     */
    public static String hMacSha1(String key, String str) {
        return encrypt("HmacSHA1", key, str);
    }

    /**
     * HmacMD5加密
     */
    public static String hMacMd5(String key, String str) {
        return encrypt("HmacMD5", key, str);
    }

}
