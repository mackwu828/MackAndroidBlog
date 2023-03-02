package com.mackwu.component.func.compress;

import android.text.TextUtils;

import com.mackwu.component.util.ByteUtil;
import com.mackwu.component.util.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * ===================================================
 * Created by MackWu on 2022/3/17 16:06
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class FileFormatUtil {

    public enum FileFormat {

        UNKNOWN(""),

        JPEG("FFD8FF"),

        PNG("89504E47"),

        GIF("47494638"),

        TIFF("49492A00"),

        BMP("424D");

        private final String magicNumber;

        FileFormat(String magicNumber) {
            this.magicNumber = magicNumber;
        }

        public String getMagicNumber() {
            return magicNumber;
        }
    }

    /**
     * 获取文件扩展名
     *
     * @param filePath 文件路径
     */
    public static String getFileExtension(String filePath) {
        String fileExtension = "";
        if (!TextUtils.isEmpty(filePath)) {
            fileExtension = filePath.toLowerCase(Locale.ENGLISH).substring(filePath.lastIndexOf(".") + 1);
        }
        return fileExtension;
    }

    /**
     * 获取文件魔数
     *
     * @param filePath 文件路径
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static String getFileMagicNumber(String filePath) {
        InputStream inputStream = null;
        try {
            File file = new File(filePath);
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[4];
            inputStream.read(bytes);
            return ByteUtil.bytesToHex(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(inputStream);
        }
        return "";
    }

    /**
     * 获取文件格式
     *
     * @param filePath 文件路径
     */
    public static FileFormat getFileFormat(String filePath) {
        String magicNumber = getFileMagicNumber(filePath);
        FileFormat fileFormat = FileFormat.UNKNOWN;
        if (magicNumber.contains(FileFormat.JPEG.getMagicNumber())) {
            fileFormat = FileFormat.JPEG;
        } else if (magicNumber.contains(FileFormat.PNG.getMagicNumber())) {
            fileFormat = FileFormat.PNG;
        } else if (magicNumber.contains(FileFormat.GIF.getMagicNumber())) {
            fileFormat = FileFormat.GIF;
        } else if (magicNumber.contains(FileFormat.TIFF.getMagicNumber())) {
            fileFormat = FileFormat.TIFF;
        } else if (magicNumber.contains(FileFormat.BMP.getMagicNumber())) {
            fileFormat = FileFormat.BMP;
        }
        return fileFormat;
    }

    /**
     * 是否是JPEG图片
     *
     * @param filePath 文件路径
     */
    public static boolean isJpegImage(String filePath) {
        return getFileFormat(filePath) == FileFormat.JPEG;
    }

}
