package com.mackwu.component.util;

/**
 * ===================================================
 * Created by MackWu on 2021/10/19 16:38
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class ByteUtil {

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

}
