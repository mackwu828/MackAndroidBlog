package com.mackwu.kt.other;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * ===================================================
 * Created by MackWu on 2019/12/25 15:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class IO {

    /**
     * 文件读写。从源文件读取，写入目标文件。write(byte b[])
     * 比如一个100字节的文件，每次读取1024个字节。写入的时候用的write(byte b[])，那么也写入1024个字节的大小，而不是100个字节。此时目标文件大小是1024个字节
     */
    public static void io1(File sourceFile, File destFile) {
        FileInputStream fis;
        FileOutputStream fos;
        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            while (fis.read(buf) > 0) {
                fos.write(buf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // todo
        }
    }

    /**
     * 文件读写。从源文件读取，写入目标文件。write(byte b[], int off, int len)
     * 比如一个100字节的文件，每次读取1024个字节。写入的时候用的write(byte b[], int off, int len)，那么会写入len个字节，此时目标文件大小是100个字节
     */
    public static void io2(File sourceFile, File destFile) {
        FileInputStream fis;
        FileOutputStream fos;
        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
                fos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // todo
        }
    }

    public static void main(String[] args) {
        File sourceFile = new File("C:/Android/text.txt");
        System.out.println("sourceFile exists: " + sourceFile.exists());
        System.out.println("sourceFile isFile: " + sourceFile.isFile());
        System.out.println("sourceFile length: " + sourceFile.length());

        File destFile = new File("C:/Android/text2.txt");
        IO.io2(sourceFile, destFile);

        System.out.println("destFile exists: " + destFile.exists());
        System.out.println("destFile isFile: " + destFile.isFile());
        System.out.println("destFile length: " + destFile.length());
    }
}
