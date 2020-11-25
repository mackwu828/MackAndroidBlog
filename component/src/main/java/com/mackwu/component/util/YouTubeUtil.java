package com.mackwu.component.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * ===================================================
 * Created by MackWu on 2020/8/25 15:16
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class YouTubeUtil {

    private static final String YOUTUBE_PACKAGE = "com.google.android.youtube.tv";
    private static final String SMART_YOUTUBE_PACKAGE = "com.liskovsoft.videomanager";
    private static final String VIDEO_URL = "http://www.youtube.com/watch?v=";

    public interface YouTubeInstallListener{
        void onTouTubeNotInstall();
    }

    /**
     * 跳转到youtube。判断是否有安装youtube
     * @param context 上下文
     * @param videoId 视频id
     * @param isYouTubeFirst true先使用youtube，false先使用smart youtube
     * @param listener 没安装youtube回调
     */
    public static void start(Context context, String videoId, boolean isYouTubeFirst, YouTubeInstallListener listener){
        if (isYouTubeFirst) {
            if (PackageUtil.isAppInstalled(context, YOUTUBE_PACKAGE)) {
                startYoutube(context, videoId);
                return;
            }
            if (PackageUtil.isAppInstalled(context, SMART_YOUTUBE_PACKAGE)) {
                startSmartYoutube(context, videoId);
                return;
            }
        } else {
            if (PackageUtil.isAppInstalled(context, SMART_YOUTUBE_PACKAGE)) {
                startSmartYoutube(context, videoId);
                return;
            }
            if (PackageUtil.isAppInstalled(context, YOUTUBE_PACKAGE)) {
                startYoutube(context, videoId);
                return;
            }
        }
        listener.onTouTubeNotInstall();
    }

    /**
     * 跳转到youtube
     *
     * @param context 上下文
     * @param videoId 视频id
     */
    public static void startYoutube(Context context, String videoId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(VIDEO_URL + videoId));
        String youtubeActivityName = PackageUtil.getActivityName(context, YOUTUBE_PACKAGE);
        if (!TextUtils.isEmpty(youtubeActivityName)) {
            intent.setClassName(YOUTUBE_PACKAGE, youtubeActivityName);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到smart youtube
     *
     * @param context 上下文
     * @param videoId 视频id
     */
    public static void startSmartYoutube(Context context, String videoId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(VIDEO_URL + videoId));
        String youtubeActivityName = PackageUtil.getActivityName(context, SMART_YOUTUBE_PACKAGE);
        if (!TextUtils.isEmpty(youtubeActivityName)) {
            intent.setClassName(SMART_YOUTUBE_PACKAGE, youtubeActivityName);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
