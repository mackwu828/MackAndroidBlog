package com.mackwu.storage.util;

import android.graphics.Bitmap;
import android.os.Build;

import java.util.Locale;

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
        return stringBuilder.toString().trim().toUpperCase(Locale.ENGLISH);
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
     * 字节转化字符串
     * 1GB=102mb
     * 1MB=1024kb
     * 1Kb=1024bytes
     *
     * @param bytes 字节数
     * @param unit  单位。1024、或者1000
     */
    private static String bytesToStr(long bytes, long unit) {
        if (bytes < unit) {
            return bytes + "bytes";
        } else if (bytes < unit * unit) {
            return String.format(Locale.getDefault(), "%.2f", bytes / unit * 1.0f) + "KB";
        } else if (bytes < unit * unit * unit) {
            return String.format(Locale.getDefault(), "%.2f", bytes / unit * 1.0f / unit) + "MB";
        } else {
            return String.format(Locale.getDefault(), "%.2f", bytes / unit * 1.0f / unit / unit) + "GB";
        }
    }

    public static String bytesToStr(long bytes) {
        return bytesToStr(bytes, 1024);
    }

    public static String bytesToStrByAndroidVersion(long bytes) {
        return bytesToStr(bytes, Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? 1000 : 1024);
    }

    /**
     * 获取 bitmap 内存大小。如 getByteCount=8294400(7.91MB)
     *
     * @param bitmap bitmap
     */
    public static String getByteCount(Bitmap bitmap) {
        if (bitmap != null) {
            return "getByteCount=" + bitmap.getByteCount() + "(" + bytesToStr(bitmap.getByteCount()) + ")";
        }
        return "bitmap is null";
    }

}
