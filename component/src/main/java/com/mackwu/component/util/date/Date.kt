package com.mackwu.component.util.date

import android.util.Log
import java.util.*


/**
 * ===================================================
 * Created by MackWu on 2020/4/10 18:45
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

private val calendar = Calendar.getInstance()

/**
 * 年
 */
val year: Int
    get() = calendar.get(Calendar.YEAR)

/**
 * 月。注：月返回0-11。需要加1
 */
val month: Int
    get() = calendar.get(Calendar.MONTH) + 1

/**
 * 日
 */
val day: Int
    get() = calendar.get(Calendar.DAY_OF_MONTH)

/**
 * 获取波斯历
 */
fun getJalaliDate(): String {
    Log.d("TAG", "$year, $month, $day")
    return JalaliCalendar.gregorianToJalali(JalaliCalendar.YearMonthDate(year, month, day)).toString()
}

/**
 * 获取格式化的波斯历。月日个位数时补0。
 */
fun getFormatJalaliDate(): String {
    val jalaliDate = getJalaliDate()
    val dates = jalaliDate.split("/")
    val dates0 = dates[0]
    var dates1 = dates[1]
    var dates2 = dates[2]
    if (dates1.length == 1) dates1 = "0$dates1"
    if (dates2.length == 1) dates2 = "0$dates2"
    return "$dates0/$dates1/$dates2"
}