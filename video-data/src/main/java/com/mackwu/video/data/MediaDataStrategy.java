package com.mackwu.video.data;

import android.media.MediaMetadataRetriever;

/**
 * @author MackWu
 * @since 2023/3/10 16:16
 * 基于系统的MediaMetadataRetriever
 * 缺陷：某些非主流的视频格式无法获取视频信息和首帧图片
 */
public class MediaDataStrategy {

    private String filePath;
    private MediaMetadataRetriever metadataRetriever;

    public MediaDataStrategy(String filePath) {
        metadataRetriever = new MediaMetadataRetriever();
        metadataRetriever.setDataSource(filePath);
    }

//    @Override
//    public VideoData getVideoData() {
//
//        return null;
//    }
//
//    @Override
//    public void getVideoFirstFrame() {
//        Bitmap firstFrameBitmap = metadataRetriever.getFrameAtTime(0);
//    }

}
