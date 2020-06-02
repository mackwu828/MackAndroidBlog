package com.mackwu.component.util.date;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;

import com.mackwu.component.R;

public class PersianCalendarAlgorithm {
    private String TAG = "PersianCalendarAlgorithm";
    private boolean DEBUG = false;
    private static PersianCalendarAlgorithm mPersianCalendarAlgorithm;
    private double GREGORIAN_EPOCH = 1721425.5;
    private double PERSIAN_EPOCH = 1948320.5;
    private Time gregorianTime = null;
    private Context mContext;
    private int persianWeekDay = 0;
    private int month = 0;

    private PersianCalendarAlgorithm(Context context) {
        mContext = context;
    }

    private PersianCalendarAlgorithm() {
    }

    public static PersianCalendarAlgorithm getInstance(Context context) {
        if (mPersianCalendarAlgorithm == null) {
            mPersianCalendarAlgorithm = new PersianCalendarAlgorithm(context);
        }
        return mPersianCalendarAlgorithm;
    }

    public static PersianCalendarAlgorithm getInstance() {
        if (mPersianCalendarAlgorithm == null) {
            mPersianCalendarAlgorithm = new PersianCalendarAlgorithm();
        }
        return mPersianCalendarAlgorithm;
    }

    /**
     * Gregorian calendar is converted into Persian
     *
     * @param t
     * @return
     */
    public Time GregorianToPersian(Time t) {
        gregorianTime = t;
        int Year, Month, Day;
        double Jd;
        Month = gregorianTime.month + 1;//[0,11]
        Day = gregorianTime.monthDay;
        Year = gregorianTime.year;
        if (DEBUG) {
            Log.i(TAG, "Gregorian Year =" + Year);
            Log.i(TAG, "Gregorian Month =" + Month);
            Log.i(TAG, "Gregorian Day =" + Day);
        }
        Jd = GregorianToJd(Year, Month, Day);

        return JdToPersian(Jd);

    }

    public double GregorianToJd(int Year, int Month, int Day)//公历转换成JD
    {
        return (GREGORIAN_EPOCH - 1) +
                (365 * (Year - 1)) +
                Math.floor((Year - 1) / 4) +
                (-Math.floor((Year - 1) / 100)) + Math.floor((Year - 1) / 400) +
                Math.floor((((367 * Month) - 362) / 12) +
                        ((Month <= 2) ? 0 : (leap_gregorian(Year) ? -1 : -2)) +
                        Day);
    }

    public Time JdToPersian(double Jd)//Jd 转换成波斯历
    {

        double depoch;
        int cycle, cyear, aux1, aux2, ycycle;
        int year, day;
        double yday;
        Jd = Math.floor(Jd) + 0.5;
        depoch = Jd - PersianToJd(475, 1, 1);
        cycle = (int) Math.floor(depoch / 1029983);
        cyear = (int) (((long) depoch) % 1029983);
        if (cyear == 1029982) {
            ycycle = 2820;
        } else {
            aux1 = (int) Math.floor(cyear / 366);
            aux2 = cyear % 366;
            ycycle = (int) (Math.floor(((2134 * aux1) + (2816 * aux2) + 2815) / 1028522) + aux1 + 1);
        }
        year = ycycle + (2820 * cycle) + 474;
        if (year <= 0)
            year--;
        yday = (Jd - PersianToJd(year, 1, 1)) + 1;
        month = (int) ((yday <= 186) ? Math.ceil(yday / 31) : Math.ceil((yday - 6) / 30));
        day = (int) ((Jd - PersianToJd(year, month, 1)) + 1);
        if (DEBUG) {
            Log.i(TAG, "Persian Year =" + year);
            Log.i(TAG, "Persian Month =" + month);
            Log.i(TAG, "Persian Day =" + day);
        }


        if (gregorianTime.weekDay < 6) {
            persianWeekDay = gregorianTime.weekDay + 2;
        } else {
            persianWeekDay = gregorianTime.weekDay - 5;
        }
        Time persianTime = new Time();
        persianTime.year = year;
        persianTime.month = month - 1;
        persianTime.monthDay = day;
        persianTime.hour = gregorianTime.hour;
        persianTime.minute = gregorianTime.minute;
        persianTime.second = gregorianTime.second;
        persianTime.weekDay = persianWeekDay;
        return persianTime;

    }

    public double PersianToJd(int Year, int Month, int Day) {
        int epbase, epyear;
        epbase = Year - ((Year >= 0) ? 474 : 473);
        epyear = 474 + (epbase % 2820);

        return Day +
                ((Month <= 7) ? ((Month - 1) * 31) : (((Month - 1) * 30) + 6)) +
                Math.floor(((epyear * 682) - 110) / 2816) +
                (epyear - 1) * 365 +
                Math.floor(epbase / 2820) * 1029983 +
                (PERSIAN_EPOCH - 1);
    }

    public boolean leap_gregorian(int year) {
        return ((year % 4) == 0) && (!(((year % 100) == 0) && ((year % 400) != 0)));
    }

    /**
     * Persian convert Gregorian calendar
     */
    public Time PersianToGregorian(int Year_Persian, int Month_Persian, int Day_Persian) {
        double jd;
        jd = PersianToJd(Year_Persian, Month_Persian, Day_Persian);
        return jd_to_gregorian(jd);
    }

    public Time jd_to_gregorian(double jd) {
        double wjd, depoch, yearday;
        int quadricent, dqc, cent, dcent, quad, dquad, yindex, leapadj;
        int year, month, day;
        wjd = Math.floor(jd - 0.5) + 0.5;
        depoch = wjd - GREGORIAN_EPOCH;
        quadricent = (int) Math.floor(depoch / 146097);
        dqc = (int) (((long) depoch) % 146097);
        cent = (int) Math.floor(dqc / 36524);
        dcent = (dqc % 36524);
        quad = (int) Math.floor(dcent / 1461);
        dquad = (dcent % 1461);
        yindex = (int) Math.floor(dquad / 365);
        year = (quadricent * 400) + (cent * 100) + (quad * 4) + yindex;
        if (!((cent == 4) || (yindex == 4))) {
            year++;
        }
        yearday = wjd - GregorianToJd(year, 1, 1);
        leapadj = ((wjd < GregorianToJd(year, 3, 1)) ? 0
                :
                (leap_gregorian(year) ? 1 : 2)
        );
        month = (int) Math.floor((((yearday + leapadj) * 12) + 373) / 367);
        day = (int) ((wjd - GregorianToJd(year, month, 1)));
        Time gregorianTime = new Time();
        gregorianTime.year = year;
        gregorianTime.month = month - 1;
        gregorianTime.monthDay = day;
        Log.d(TAG, "year = " + year + "month = " + month + "day =" + day);
        return gregorianTime;

    }

    public String getPersianMonth() {
        String[] mMonthOfPersian = mContext.getResources().getStringArray(R.array.persianmonth);
        return mMonthOfPersian[month - 1];
    }

    public String getPersianWeekDay() {
        String[] mWeekOfPersion = mContext.getResources().getStringArray(R.array.persianweek);
        return mWeekOfPersion[persianWeekDay - 1];
    }

    /**
     * 获取波斯历
     * 公历转波斯历在线转换：https://cn.calcuworld.com/%E6%B3%A2%E6%96%AF%E6%97%A5%E5%8E%86
     */
    public String getJalaliDate() {
        Time time = new Time();
        time.set(System.currentTimeMillis());
        PersianCalendarAlgorithm mPersianCalendarAlgorithm = PersianCalendarAlgorithm.getInstance(mContext);
        Time mTimeOfPersian = mPersianCalendarAlgorithm.GregorianToPersian(time);
        String newDate = mPersianCalendarAlgorithm.getPersianMonth() + "/" + mTimeOfPersian.format("%d") + "/" + mTimeOfPersian.format("%Y");
        return newDate;
    }

}