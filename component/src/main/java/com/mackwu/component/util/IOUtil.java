package com.mackwu.component.util;

import android.content.Context;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

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
            if (autoCloseable != null) {
                try {
                    autoCloseable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    /**
//     * 文件拷贝到目标文件。
//     * 从源文件读取，写入目标文件。write(byte b[], int off, int len)，每次写入[0~len]，len最大为缓冲区大小。
//     * 比如读写一个1124字节的文件，缓冲区为1024个字节，先读写1个缓存区大小，再读写时是100个字节，小于缓冲区大小，则最终目标文件大小是1124个字节。
//     *
//     * @param sourceFile 源文件
//     * @param destFile   目标文件
//     */
//    public static long copy(File sourceFile, File destFile) {
//        FileInputStream fis = null;
//        FileOutputStream fos = null;
//        try {
//            fis = new FileInputStream(sourceFile);
//            fos = new FileOutputStream(destFile);
//            byte[] buf = new byte[BUFFER_SIZE];
//            int len;
//            while ((len = fis.read(buf)) != -1) {
//                fos.write(buf, 0, len);
//                fos.flush();
//            }
//            return destFile.length();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            close(fis, fos);
//        }
//        return -1;
//    }

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
            return destFile.length();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(src, dst);
        }
        return -1;
    }

    /**
     * 输入流拷贝到目标文件。
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
            return destFile.length();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(inputStream, fos);
        }
        return -1;
    }

    /**
     * 字符串拷贝到目标文件。
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
     * asset文件拷贝到目标文件。
     *
     * @param context       上下文
     * @param assetFileName asset文件名称
     * @param destFile      目标文件
     */
    public static long copy(Context context, String assetFileName, File destFile) {
        InputStream inputStream = null;
        FileOutputStream fos = null;
        try {
            inputStream = context.getAssets().open(assetFileName);
            fos = new FileOutputStream(destFile);
            byte[] buf = new byte[BUFFER_SIZE];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                fos.write(buf, 0, len);
                fos.flush();
            }
            return destFile.length();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(inputStream, fos);
        }
        return -1;
    }

    /**
     * 文件转字节数组
     *
     * @param file 文件
     */
    public static byte[] toBytes(File file) {
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                bos.flush();
            }
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fis, bos);
        }
        return null;
    }

    /**
     * 输入流转字节数组
     *
     * @param inputStream 输入流
     */
    public static byte[] toBytes(InputStream inputStream) {
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
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
     * 字节数组转输入流
     *
     * @param bytes 字节数组
     */
    public static InputStream toInputStream(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

    /**
     * 字节数组转字符串
     *
     * @param bytes 字节数组
     */
    public static String toString(byte[] bytes) {
        return new String(bytes);
    }

    /**
     * 输入流转字符串
     *
     * @param inputStream 输入流
     */
    public static String toString(InputStream inputStream) {
        return toString(toBytes(inputStream));
    }

    /**
     * 文件转字符串
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

    /**
     * asset文件转字符串
     *
     * @param context       上下文
     * @param assetFileName asset文件名称
     */
    public static String toString(Context context, String assetFileName) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(assetFileName);
            byte[] bytes = toBytes(inputStream);
            return toString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inputStream);
        }
        return "";
    }

    /**
     * 获取文件md5值
     *
     * @param file 文件
     */
    public static String toMd5String(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            MappedByteBuffer byteBuffer = fis.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            return bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fis);
        }
        return "";
    }

    /**
     * 删除文件夹。如果是文件夹，则递归调用删除文件夹里所有文件。如果是文件，直接删除。
     *
     * @param file 文件夹
     */
    public static void deleteDir(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    deleteDir(f);
                }
            }
        }
        //noinspection ResultOfMethodCallIgnored
        file.delete();
    }

    /**
     * 获取文件夹大小
     *
     * @param file 文件夹
     */
    public static long getDirSize(File file) {
        long length = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    length += getDirSize(f);
                }
            }
        }
        length += file.length();
        return length;
    }

}
