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
public final class DigestUtil {

    /**
     * 字节数组转十六进制字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (final byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            stringBuilder.append((hex.length() == 1) ? "0" + hex : hex);
        }
        return stringBuilder.toString().trim();
    }

    /**
     * 十六进制字符转串字节数组
     */
    public static byte[] hexToBytes(String hex) {
        int m, n;
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + hex.substring(i * 2, m) + hex.substring(m, n));
            bytes[i] = (byte) intVal;
        }
        return bytes;
    }

    /**
     * 加密
     *
     * @param algorithm 算法
     * @param str       需要加密的字符串
     */
    private static String messageDigest(String algorithm, String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] result = digest.digest(str.getBytes());
            return bytesToHex(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * MD5加密
     */
    public static String md5(String str) {
        return messageDigest("MD5", str);
    }

    /**
     * SHA-1加密
     */
    public static String sha1(String str) {
        return messageDigest("SHA-1", str);
    }

    /**
     * SHA-256加密
     */
    public static String sha256(String str) {
        return messageDigest("SHA-256", str);
    }

    /**
     * 加密
     *
     * @param algorithm 算法
     * @param key       key
     * @param str       需要加密的字符串
     */
    public static String secretKeySpec(String algorithm, String key, String str) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(secretKey);
            byte[] result = mac.doFinal(str.getBytes());
            return Base64.encodeToString(result, Base64.NO_WRAP);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * HmacSHA1加密
     */
    public static String hMacSha1(String key, String str) {
        return secretKeySpec("HmacSHA1", key, str);
    }

    /**
     * HmacMD5加密
     */
    public static String hMacMd5(String key, String str) {
        return secretKeySpec("HmacMD5", key, str);
    }
}
