package com.mackwu.image.rotate.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/**
 * ===================================================
 * Created by MackWu on 2019/12/25 15:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class IOUtil {

    public static final int BUFFER_SIZE = 1024 * 4;

    /**
     * 关闭IO流
     */
    public static void close(AutoCloseable... autoCloseables) {
        for (AutoCloseable autoCloseable : autoCloseables) {
            if (null != autoCloseable) {
                try {
                    autoCloseable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 拷贝文件。
     *
     * @param sourceFile 源文件
     * @param destFile   目标文件
     */
    public static long copy(File sourceFile, File destFile) {
        FileChannel src = null;
        FileChannel dst = null;
        try {
            src = new FileInputStream(sourceFile).getChannel();
            dst = new FileOutputStream(destFile).getChannel();
            dst.transferFrom(src, 0, src.size());
            dst.force(false);
            return destFile.length();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(src, dst);
        }
        return -1;
    }

    /**
     * 拷贝文件。
     *
     * @param sourceFile 源文件
     * @param destFile   目标文件
     */
    public static long copyNoCatch(File sourceFile, File destFile) throws Exception {
        FileChannel src = null;
        FileChannel dst = null;
        try {
            src = new FileInputStream(sourceFile).getChannel();
            dst = new FileOutputStream(destFile).getChannel();
            dst.transferFrom(src, 0, src.size());
            dst.force(false);
            return destFile.length();
        } finally {
            IOUtil.close(src, dst);
        }
    }

    /**
     * 拷贝文件。
     *
     * @param inputStream 源文件输入流
     * @param destFile    目标文件
     */
    public static long copy(InputStream inputStream, File destFile) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(destFile);
            byte[] buf = new byte[BUFFER_SIZE];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                fos.write(buf, 0, len);
                fos.flush();
            }
            fos.getFD().sync();
            return destFile.length();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(inputStream, fos);
        }
        return -1;
    }

    /**
     * 拷贝文件。
     *
     * @param sourceStr 源文件字符串
     * @param destFile  目标文件
     */
    public static long copy(String sourceStr, File destFile) {
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(sourceStr.getBytes());
            return copy(bis, destFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(bis);
        }
        return -1;
    }

    /**
     * 转字节数组
     *
     * @param inputStream 输入流
     */
    public static byte[] toBytes(InputStream inputStream) {
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                bos.flush();
            }
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(inputStream, bos);
        }
        return null;
    }

    /**
     * 转字符串
     *
     * @param bytes 字节数组
     */
    public static String toString(byte[] bytes) {
        return new String(bytes);
    }

    /**
     * 转字符串
     *
     * @param inputStream 输入流
     */
    public static String toString(InputStream inputStream) {
        return toString(toBytes(inputStream));
    }

    /**
     * 转字符串
     *
     * @param file 文件
     */
    public static String toString(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            return toString(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            close(fis);
        }
        return "";
    }

}
