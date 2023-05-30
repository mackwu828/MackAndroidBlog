package com.mackwu.video.data;


import com.mackwu.video.data.bean.VideoData;

/**
 * @author MackWu
 * @since 2023/2/1 15:39
 */
public class VideoDataRetriever {

    private final VideoDataStrategy videoDataStrategy;

    public VideoDataRetriever(String filePath) {
        this.videoDataStrategy = new FFmpegKitStrategy(filePath);
    }

    public VideoData getVideoData() {
        return videoDataStrategy.getVideoData();
    }

    /**
     * 获取视频首帧
     */
    public void getVideoFirstFrame() {

    }

}
