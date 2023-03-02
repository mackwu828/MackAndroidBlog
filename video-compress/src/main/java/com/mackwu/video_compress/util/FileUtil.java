package com.mackwu.video_compress.util;

import java.io.File;

/**
 * @author MackWu
 * @since 2023/2/2 18:14
 */
public class FileUtil {

    /**
     * 获取文件名。带后缀。
     *
     * 例子：/storage/C8F3-D655/源视频.mp4 => 源视频.mp4
     *
     * @param filePath 文件路径
     */
    public static String getFileName(String filePath) {
        if (isSpace(filePath)) return filePath;
        int lastSep = filePath.lastIndexOf(File.separator);
        return lastSep == -1 ? filePath : filePath.substring(lastSep + 1);
    }

    /**
     * 获取文件名称。无后缀。
     * <p>
     * 例子：/storage/C8F3-D655/源视频.mp4 => 源视频
     *
     * @param filePath 文件路径
     */
    public static String getFileNameNoExtension(String filePath) {
        if (isSpace(filePath)) return filePath;
        int lastPoi = filePath.lastIndexOf('.');
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastSep == -1) {
            return (lastPoi == -1 ? filePath : filePath.substring(0, lastPoi));
        }
        if (lastPoi == -1 || lastSep > lastPoi) {
            return filePath.substring(lastSep + 1);
        }
        return filePath.substring(lastSep + 1, lastPoi);
    }

    /**
     * 获取文件的全路径。去掉文件名。
     *
     * 例子：/storage/C8F3-D655/源视频.mp4 => /storage/C8F3-D655/
     *
     * @param filePath 文件路径
     */
    public static String getDirPath(String filePath) {
        if (isSpace(filePath)) return filePath;
        int lastSep = filePath.lastIndexOf(File.separator);
        return lastSep == -1 ? "" : filePath.substring(0, lastSep + 1);
    }

    /**
     * 获取文件后缀名。
     * 例子：/storage/C8F3-D655/源视频.mp4 => mp4
     *
     * @param filePath 文件路径
     */
    public static String getFileExtension(String filePath) {
        if (isSpace(filePath)) return filePath;
        int lastPoi = filePath.lastIndexOf('.');
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastPoi == -1 || lastSep >= lastPoi) return "";
        return filePath.substring(lastPoi + 1);
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     */
    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }

}
