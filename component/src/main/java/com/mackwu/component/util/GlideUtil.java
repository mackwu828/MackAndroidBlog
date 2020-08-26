package com.mackwu.component.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * ===================================================
 * Created by MackWu on 2020/8/24 14:53
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class GlideUtil {

    public static void loadImage(Context context, int id, ImageView imageView) {
        Glide.with(context).load(id).into(imageView);
    }
}
