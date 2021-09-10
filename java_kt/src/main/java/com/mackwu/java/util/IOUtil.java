package com.mackwu.java.util;

import android.content.Context;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ===================================================
 * Created by MackWu on 2019/12/25 15:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class IOUtil {

    /**
     * 关闭IO流
     *
     * @param closeables Closeable
     */
    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (null != closeable) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    /**
//     * 拷贝文件。
//     * 从源文件读取，写入目标文件。write(byte b[])，每次写入缓冲区大小。
//     * 比如读写一个1124字节的文件，缓冲区为1024个字节，每次都会读写1个缓冲区大小，则最终目标文件大小是2048个字节。
//     */
//    @Deprecated
//    public static void copy(File sourceFile, File destFile) {
//        FileInputStream fis = null;
//        FileOutputStream fos = null;
//        try {
//            fis = new FileInputStream(sourceFile);
//            fos = new FileOutputStream(destFile);
//            byte[] buf = new byte[1024];
//            while (fis.read(buf) > 0) {
//                fos.write(buf);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            close(fis, fos);
//        }
//    }

    /**
     * 拷贝文件。
     * 从源文件读取，写入目标文件。write(byte b[], int off, int len)，每次写入[0~len]，len最大为缓冲区大小。
     * 比如读写一个1124字节的文件，缓冲区为1024个字节，先读写1个缓存区大小，再读写时是100个字节，小于缓冲区大小，则最终目标文件大小是1124个字节。
     *
     * @param sourceFile 源文件
     * @param destFile   目标文件
     */
    public static void copy(File sourceFile, File destFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
                fos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(fis, fos);
        }
    }

    /**
     * 拷贝文件。
     * 从源输入流写入目标文件。
     *
     * @param inputStream 源输入流
     */
    public static void copy(InputStream inputStream, File destFile) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                fos.write(buf, 0, len);
                fos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inputStream, fos);
        }
    }

    /**
     * 拷贝文件
     * @param context 上下文
     * @param assetFileName asset文件名称
     * @param destFile 目标文件
     */
    public static void copy(Context context, String assetFileName, File destFile) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(assetFileName);
            copy(inputStream, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inputStream);
        }
    }

    public static void main(String[] args) {
//        File sourceFile = new File("C:/Android/text.txt");
//        System.out.println("sourceFile exists: " + sourceFile.exists());
//        System.out.println("sourceFile isFile: " + sourceFile.isFile());
//        System.out.println("sourceFile length: " + sourceFile.length());
//
//        File destFile = new File("C:/Android/text2.txt");
//        IO.io2(sourceFile, destFile);
//
//        System.out.println("destFile exists: " + destFile.exists());
//        System.out.println("destFile isFile: " + destFile.isFile());
//        System.out.println("destFile length: " + destFile.length());
    }
}
