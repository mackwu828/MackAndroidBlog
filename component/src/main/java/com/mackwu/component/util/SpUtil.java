package com.mackwu.component.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.mackwu.base.util.Logger;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ===================================================
 * Created by MackWu on 2020/6/30 18:56
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class SpUtil {

    private static SharedPreferences getSp(Context context) {
//        return PreferenceManager.getDefaultSharedPreferences(context);
        return context.getSharedPreferences("sp_file", Context.MODE_PRIVATE);
    }

    /**
     * 保存数据
     */
    public static void put(Context context, String key, Object obj) {
        SharedPreferences.Editor editor = getSp(context).edit();
        if (obj instanceof Integer) {
            editor.putInt(key, (int) obj);
        } else if (obj instanceof Long) {
            editor.putLong(key, (long) obj);
        } else if (obj instanceof Float) {
            editor.putFloat(key, (float) obj);
        } else if (obj instanceof Boolean) {
            editor.putBoolean(key, (boolean) obj);
        } else {
            editor.putString(key, (String) obj);
        }
        editor.commit();
    }

    /**
     * 获取数据
     */
    public static Object get(Context context, String key, Object defaultObj) {
        SharedPreferences sp = getSp(context);
        if (defaultObj instanceof Integer) {
            return sp.getInt(key, (int) defaultObj);
        } else if (defaultObj instanceof Long) {
            return sp.getLong(key, (long) defaultObj);
        } else if (defaultObj instanceof Float) {
            return sp.getFloat(key, (float) defaultObj);
        } else if (defaultObj instanceof Boolean) {
            return sp.getBoolean(key, (boolean) defaultObj);
        } else {
            return sp.getString(key, (String) defaultObj);
        }
    }

    /**
     * 删除指定数据
     */
    public static void remove(Context context, String key) {
        SharedPreferences.Editor editor = getSp(context).edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 删除所有数据
     */
    public static void clear(Context context) {
        SharedPreferences.Editor editor = getSp(context).edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 保存数据
     */
    public static void put(String key, Serializable serializable) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(serializable);
            String objectStr = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            Logger.d("objectStr: " + objectStr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(baos, oos);
        }
    }

}
