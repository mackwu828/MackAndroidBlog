package com.mackwu.component.util;


/**
 * ===================================================
 * Created by MackWu on 2020/11/13 14:38
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ReflectUtil {

    // 创建对象
    public static void reflectNewInstance() {
        try {
            Class<?> classBook = Class.forName("com.android.peter.reflectdemo.Book");
            Object objectBook = classBook.newInstance();
//            Book book = (Book) objectBook;
//            book.setName("Android进阶之光");
//            book.setAuthor("刘望舒");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
