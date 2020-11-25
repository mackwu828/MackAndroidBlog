package com.mackwu.component.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

/**
 * ===================================================
 * Created by MackWu on 2020/8/31 11:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class GlideUtil {

    /**
     * 加载图片
     *
     * @param context   上下文
     * @param imageView imageView
     * @param url       图片地址
     */
    public static void loadImage(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    /**
     * 加载图片
     *
     * @param context       上下文
     * @param imageView     imageView
     * @param url           图片地址
     * @param placeholderId 默认图片id
     * @param errorId       错误图片id
     */
    public static void loadImage(Context context, ImageView imageView, String url, int placeholderId, int errorId) {
        RequestOptions requestOptions = RequestOptions
                .placeholderOf(placeholderId)
                .error(errorId);
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context   上下文
     * @param imageView imageView
     * @param url       图片地址
     */
    public static void loadRoundImage(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param context        上下文
     * @param imageView      imageView
     * @param url            图片地址
     * @param roundingRadius 圆角半径
     */
    public static void loadRoundCornerImage(Context context, ImageView imageView, String url, int roundingRadius) {
        RoundedCorners roundedCorners = new RoundedCorners(roundingRadius);
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.bitmapTransform(roundedCorners))
                .into(imageView);
    }

}
