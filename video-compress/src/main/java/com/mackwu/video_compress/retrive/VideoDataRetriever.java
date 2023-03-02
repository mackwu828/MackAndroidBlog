package com.mackwu.video_compress.retrive;

import com.mackwu.video_compress.bean.VideoData;

/**
 * @author MackWu
 * @since 2023/2/1 15:39
 */
public interface VideoDataRetriever {

    /**
     * 获取视频数据
     */
    VideoData getVideoData();
}
