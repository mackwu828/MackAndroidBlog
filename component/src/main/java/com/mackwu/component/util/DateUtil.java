package com.mackwu.component.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.format.DateFormat;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * ===================================================
 * Created by MackWu on 2020/8/25 16:52
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class DateUtil {

    /**
     * calendar
     */
    private static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 获取年
     */
    public static int getYear() {
        return getCalendar().get(Calendar.YEAR);
    }

    /**
     * 获取月。月份从0开始需要加1
     */
    public static int getMonth() {
        return getCalendar().get(Calendar.MONTH) + 1;
    }

    /**
     * 获取星期几
     */
    public static int getDayOfWeek() {
        return getCalendar().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取日
     */
    public static int getDayOfMonth() {
        return getCalendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取时。12小时制
     */
    public static int getHour12HourFormat() {
        return getCalendar().get(Calendar.HOUR);
    }

    /**
     * 获取时。24小时制
     */
    public static int getHour24HourFormat() {
        return getCalendar().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取分
     */
    public static int getMinute() {
        return getCalendar().get(Calendar.MINUTE);
    }

    /**
     * int补0。
     * 如: intToStr(1)  1 => 01
     */
    public static String intComplement0(int i) {
        return String.format(Locale.getDefault(), "%02d", i);
    }

    /**
     * 是否24小时制
     */
    public static boolean is24HourFormat(Context context) {
        return DateFormat.is24HourFormat(context);
    }

    /**
     * 是否12小时制
     */
    public static boolean is12HourFormat(Context context) {
        return !DateFormat.is24HourFormat(context);
    }

    /**
     * 设置24/12小时制。
     * 命令行：settings put system time_12_24 24
     * settings get system time_12_24
     * 注：需要系统权限
     */
    public static void setHourFormat(Context context, boolean is24HourFormat) {
        Settings.System.putString(context.getContentResolver(), Settings.System.TIME_12_24, is24HourFormat ? "24" : "12");
    }

    /**
     * 是否是am
     */
    public static boolean isAm() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        return gregorianCalendar.get(GregorianCalendar.AM_PM) == Calendar.AM;
    }

    /**
     * 时间单位：AM或者PM
     */
    public static String getTimeUnit() {
        return isAm() ? "AM" : "PM";
    }


    /**
     * 获取时间分钟。
     * 例子：当前时间 => 24小时制：21:05、12小时制：09:05 PM
     *
     * @param isHourComplement0 小时是否补0
     * @param isShowTimeUnit    是否显示AM、PM
     */
    public static String getHourMinute(Context context, boolean isHourComplement0, boolean isShowTimeUnit) {
        if (DateFormat.is24HourFormat(context)) {
            String hourStr = String.valueOf(getHour24HourFormat());
            if (isHourComplement0) {
                hourStr = intComplement0(getHour24HourFormat());
            }
            return hourStr + ":" + intComplement0(getMinute());
        } else {
            String hourStr = String.valueOf(getHour12HourFormat());
            if (isHourComplement0) {
                hourStr = intComplement0(getHour12HourFormat());
            }
            if (isShowTimeUnit) {
                String timeUnit = isAm() ? "AM" : "PM";
                return hourStr + ":" + intComplement0(getMinute()) + " " + timeUnit;
            } else {
                return hourStr + ":" + intComplement0(getMinute());
            }
        }
    }

    /**
     * 时间戳转日期字符串。
     * yyyy-MM-dd HH:mm:ss => 2020-11-18 15:24:07
     * EEE, d MMM yyyy HH:mm:ss 'GMT'Wed => 12 Oct 2022 07:26:07 GMT
     * <p>
     * 月份：如当前是11月
     * MM => 11
     * MMM => Nov
     * MMMM => November
     *
     * @param time    时间戳。如System.currentTimeMillis()
     * @param pattern 时间戳格式。如 yyyy-MM-dd HH:mm:ss
     */
    public static String stampToDateStr(long time, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return simpleDateFormat.format(time);
    }

    /**
     * 日期字符串转时间戳。
     * 例子：
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     */
    public static long dateStrToStamp(String dateStr, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            Date date = simpleDateFormat.parse(dateStr);
            if (date != null) return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 日期字符串格式转换。
     *
     * @param sourceDateStr 源时间字符串
     * @param sourcePattern 源时间格式。如yyyy-MM-dd HH:mm:ss
     * @param sourceLocale  源语言
     * @param destPattern   目标时间格式
     * @param destLocale    目标语言
     */
    public static String dateStrToDateStr(String sourceDateStr, String sourcePattern, Locale sourceLocale, String destPattern, Locale destLocale) {
        try {
            SimpleDateFormat sourceFormat = new SimpleDateFormat(sourcePattern, sourceLocale);
            SimpleDateFormat destFormat = new SimpleDateFormat(destPattern, destLocale);
            Date sourceDate = sourceFormat.parse(sourceDateStr);
            if (sourceDate != null) {
                return destFormat.format(sourceDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String dateStrToDateStr(String sourceDateStr, String sourcePattern, String destPattern) {
        return dateStrToDateStr(sourceDateStr, sourcePattern, Locale.getDefault(), destPattern, Locale.getDefault());
    }

    /**
     * 时区转化
     * @param sourceStamp 源时间戳
     * @param sourceTimeZone 源时区
     * @param destTimeZone 目标时区
     * @return 目标时间戳
     */
    public static long timeZoneToTimeZone(long sourceStamp, TimeZone sourceTimeZone, TimeZone destTimeZone) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            // sourceDate
            simpleDateFormat.setTimeZone(sourceTimeZone);
            Date sourceDate = simpleDateFormat.parse(simpleDateFormat.format(new Date(sourceStamp)));
            // destDate
            if (sourceDate != null) {
                simpleDateFormat.setTimeZone(destTimeZone);
                Date destDate = simpleDateFormat.parse(simpleDateFormat.format(sourceDate));
                if (destDate != null) {
                    return destDate.getTime();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取文件修改时间。
     *
     * @param path 文件路径
     * @return 文件修改时间戳
     */
    public static long getLastModifiedTime(String path) {
        try {
            long lastModifiedTime;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                BasicFileAttributes attributes = Files.readAttributes(Paths.get(path), BasicFileAttributes.class);
                // Android8.0包括8.0后虽然可以获取文件创建时间，但是测试发现获取的时间和修改时间是一样的。
//                creationTime = attributes.creationTime().toMillis();
                lastModifiedTime = attributes.lastModifiedTime().toMillis();
            } else {
                File file = new File(path);
                lastModifiedTime = file.lastModified();
            }
            // 文件时间是东八区时间
            return timeZoneToTimeZone(lastModifiedTime, TimeZone.getTimeZone("Asia/Shanghai"), TimeZone.getDefault());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

//    public static void main(String[] args) {
//        System.out.println(dateStrToStamp("2020-11-18 15:24:07", "yyyy-MM-dd HH:mm:ss"));
//        long a = System.currentTimeMillis() - dateStrToStamp("2020-11-18 15:24:07", "yyyy-MM-dd HH:mm:ss");
//        System.out.println(a / 1000 / 60 / 60 / 24);
//    }

}
