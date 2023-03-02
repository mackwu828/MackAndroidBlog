package com.mackwu.video_compress.bean;

/**
 * @author MackWu
 * @since 2023/2/1 11:13
 */
public class VideoData {

    // 文件路径
    private String filePath;
    // 比特率。单位kbps
    private long bitrate;
    // 时长。单位秒
    private long duration;

    /*
     * 视频
     */
    // 视频宽
    private int videoWidth;
    // 视频高
    private int videoHeight;
    // 视频编码。如h264
    private String videoCodec;
    // 视频比特率。单位kbps
    private long videoBitrate;
    // 视频格式。如mp4、
    private String videoFormat;
    // 视频帧率
    private long videoFrameRate;
    // 视频旋转角度
    private int videoRotation;

    /*
     * 音频
     */
    // 音频编码
    private String audioCodec;
    // 音频比特率
    private long audioBitrate;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getBitrate() {
        return bitrate;
    }

    public void setBitrate(long bitrate) {
        this.bitrate = bitrate;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(int videoWidth) {
        this.videoWidth = videoWidth;
    }

    public int getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(int videoHeight) {
        this.videoHeight = videoHeight;
    }

    public String getVideoCodec() {
        return videoCodec;
    }

    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

    public long getVideoBitrate() {
        return videoBitrate;
    }

    public void setVideoBitrate(long videoBitrate) {
        this.videoBitrate = videoBitrate;
    }

    public String getVideoFormat() {
        return videoFormat;
    }

    public void setVideoFormat(String videoFormat) {
        this.videoFormat = videoFormat;
    }

    public long getVideoFrameRate() {
        return videoFrameRate;
    }

    public void setVideoFrameRate(long videoFrameRate) {
        this.videoFrameRate = videoFrameRate;
    }

    public int getVideoRotation() {
        return videoRotation;
    }

    public void setVideoRotation(int videoRotation) {
        this.videoRotation = videoRotation;
    }

    public String getAudioCodec() {
        return audioCodec;
    }

    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

    public long getAudioBitrate() {
        return audioBitrate;
    }

    public void setAudioBitrate(long audioBitrate) {
        this.audioBitrate = audioBitrate;
    }

    @Override
    public String toString() {
        return "VideoData{" +
                "bitrate=" + bitrate +
                ", videoDuration=" + duration +
                ", videoWidth=" + videoWidth +
                ", videoHeight=" + videoHeight +
                ", videoCodec='" + videoCodec + '\'' +
                ", videoBitrate=" + videoBitrate +
                ", videoFormat='" + videoFormat + '\'' +
                ", videoFrameRate='" + videoFrameRate + '\'' +
                ", videoRotation=" + videoRotation +
                ", audioCodec='" + audioCodec + '\'' +
                ", audioBitrate=" + audioBitrate +
                '}';
    }
}
