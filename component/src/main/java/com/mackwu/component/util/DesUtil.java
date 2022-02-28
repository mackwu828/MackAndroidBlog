package com.mackwu.component.util;

import android.util.Base64;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * ===================================================
 * Created by MackWu on 2022/1/13 16:27
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class DesUtil {

    private final static String HEX = "0123456789ABCDEF";
    private final static String TRANSFORMATION = "DES/CBC/PKCS5Padding"; // DES是加密方式 CBC是工作模式 PKCS5Padding是填充模式
    private final static String IV_PARAMETER_SPEC = "01020304"; // 初始化向量参数，AES 为16bytes. DES 为8bytes.
    private final static String ALGORITHM = "DES"; // DES是加密方式
    private static final String SHA1PRNG = "SHA1PRNG"; // SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法

    /**
     * DES加密
     * @param key 私钥
     * @param data 数据
     */
    public static String encrypt(String key, String data) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER_SPEC.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, getRawKey(key), iv);
            byte[] bytes = cipher.doFinal(data.getBytes());
            return Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * DES解密
     * @param key 私钥
     * @param data 数据
     */
    public static String decrypt(String key, String data) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER_SPEC.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, getRawKey(key), iv);
            byte[] original = cipher.doFinal(Base64.decode(data.getBytes(), Base64.DEFAULT));
            return new String(original);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static Key getRawKey(String key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(dks);
    }

    /**
     * 生成随机数，可以当做动态的密钥 加密和解密的密钥必须一致，不然将不能解密
     */
    public static String generateKey() {
        try {
            SecureRandom localSecureRandom = SecureRandom.getInstance(SHA1PRNG);
            byte[] bytes = new byte[20];
            localSecureRandom.nextBytes(bytes);
            return bytesToHex(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字节数组转十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (final byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            stringBuilder.append((hex.length() == 1) ? "0" + hex : hex);
        }
        return stringBuilder.toString().trim();
    }

}
