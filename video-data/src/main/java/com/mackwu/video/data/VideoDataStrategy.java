package com.mackwu.video.data;

import com.mackwu.video.data.bean.VideoData;

/**
 * @author MackWu
 * @since 2023/3/10 16:07
 */
public interface VideoDataStrategy {

    /**
     * 获取视频数据
     *
     * @return 视频数据
     */
    VideoData getVideoData();

    /**
     * 获取视频首帧
     */
    void getVideoFirstFrame();

}
