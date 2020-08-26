package com.mackwu.component.util;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
     * 获取日
     */
    public static int getDay() {
        return getCalendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取时
     */
    public static int getHour() {
        return getCalendar().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取时。判断是否是24小时制。
     */
    public static int getHour(Context context) {
        return DateFormat.is24HourFormat(context) ? getCalendar().get(Calendar.HOUR_OF_DAY) : getCalendar().get(Calendar.HOUR);
    }

    /**
     * 获取分
     */
    public static int getMinute() {
        return getCalendar().get(Calendar.MINUTE);
    }

    /**
     * 格式转换。int转2位数
     */
    public static String format(int time) {
        return String.format(Locale.getDefault(), "%02d", time);
    }

    /**
     * 时间字符串格式转换
     *
     * @param sourceTime    源时间字符串
     * @param sourcePattern 源时间格式
     * @param sourceLocale  源语言
     * @param destPattern   目标时间格式
     * @param destLocale    目标语言
     * @return 目标时间字符串
     */
    public static String format(String sourceTime, String sourcePattern, Locale sourceLocale, String destPattern, Locale destLocale) {
        try {
            SimpleDateFormat sourceFormat = new SimpleDateFormat(sourcePattern, sourceLocale);
            SimpleDateFormat destFormat = new SimpleDateFormat(destPattern, destLocale);
            Date sourceDate = sourceFormat.parse(sourceTime);
            if (sourceDate != null) {
                return destFormat.format(sourceDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void formatHourMinute(){
        String sourceTime = format(getHour()) + ":" + format(getMinute());
        System.out.println(sourceTime);
        System.out.println(format(sourceTime, "HH:mm", Locale.getDefault(), "hh:mm aa", Locale.ENGLISH));
    }

    public static void main(String[] args) {
        String sourceTime = format(getHour()) + ":" + format(getMinute());
        System.out.println(sourceTime);
        System.out.println(format(sourceTime, "HH:mm", Locale.getDefault(), "hh:mm aa", Locale.ENGLISH));
    }

}
