package com.mackwu.component;


import com.mackwu.base.util.Logger;
import com.mackwu.component.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * ===================================================
 * Created by MackWu on 2020/11/12 14:37
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class JavaTest {

    public static void main(String[] args) throws ParseException {

        String url = "http://XX";

        String[] urls = new String[]{
                url, "hxxx"
        };
        System.out.println(Arrays.toString(urls));
    }

}
