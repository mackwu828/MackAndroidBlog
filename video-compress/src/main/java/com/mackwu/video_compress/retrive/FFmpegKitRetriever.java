package com.mackwu.video_compress.retrive;

import android.text.TextUtils;

import com.arthenica.ffmpegkit.FFprobeKit;
import com.arthenica.ffmpegkit.MediaInformation;
import com.arthenica.ffmpegkit.MediaInformationSession;
import com.arthenica.ffmpegkit.ReturnCode;
import com.arthenica.ffmpegkit.StreamInformation;
import com.mackwu.video_compress.bean.VideoData;
import com.mackwu.video_compress.util.Logger;

import org.json.JSONArray;

import java.util.List;

/**
 * @author MackWu
 * @since 2023/2/1 15:34
 */
public class FFmpegKitRetriever implements VideoDataRetriever {

    private final String filePath;

    public FFmpegKitRetriever(String filePath) {
        this.filePath = filePath;
    }

    /**
     * "-v", "error", "-hide_banner", "-print_format", "json", "-show_format", "-show_streams", "-show_chapters", "-i"
     */
    @Override
    public VideoData getVideoData() {
        try {
            Logger.d("getVideoData by kit...  filePath=" + filePath);
            MediaInformationSession session = FFprobeKit.getMediaInformation(filePath);
            ReturnCode returnCode = session.getReturnCode();
            if (!returnCode.isValueSuccess()) {
                String failStackTrace = session.getFailStackTrace();
                Logger.d("getVideoData failed...  failStackTrace=" + failStackTrace);
                return null;
            }
            // mediaInformation
            MediaInformation mediaInformation = session.getMediaInformation();
            // VideoData
            VideoData videoData = new VideoData();
            videoData.setFilePath(filePath);
            videoData.setBitrate(getBitrate(mediaInformation));
            videoData.setDuration(getDuration(mediaInformation));
            // videoStream
            StreamInformation videoStream = getVideoStream(mediaInformation);
            if (videoStream != null) {
                videoData.setVideoWidth((int) videoStream.getWidth().longValue());
                videoData.setVideoHeight((int) videoStream.getHeight().longValue());
                videoData.setVideoCodec(videoStream.getCodec());
                videoData.setVideoBitrate(getBitrateByStream(mediaInformation, videoStream));
                videoData.setVideoFormat(videoStream.getFormat());
                videoData.setVideoFrameRate(getFrameRate(videoStream));
                videoData.setVideoRotation(getRotation(videoStream));
            }
            // audioStream
            StreamInformation audioStream = getAudioStream(mediaInformation);
            if (audioStream != null) {
                videoData.setAudioCodec(audioStream.getCodec());
                videoData.setAudioBitrate(getBitrateByStream(mediaInformation, audioStream));
            }
            Logger.d("getVideoData by kit completed...  " + videoData);
            return videoData;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    private StreamInformation getVideoStream(MediaInformation mediaInformation) {
        List<StreamInformation> streams = mediaInformation.getStreams();
        for (StreamInformation stream : streams) {
            if ("video".equalsIgnoreCase(stream.getType())) {
                return stream;
            }
        }
        return null;
    }

    private StreamInformation getAudioStream(MediaInformation mediaInformation) {
        List<StreamInformation> streams = mediaInformation.getStreams();
        for (StreamInformation stream : streams) {
            if ("audio".equalsIgnoreCase(stream.getType())) {
                return stream;
            }
        }
        return null;
    }

    /**
     * 获取视频时长
     *
     * @param mediaInformation mediaInformation
     */
    private long getDuration(MediaInformation mediaInformation) {
        String duration = mediaInformation.getDuration();
        if (TextUtils.isEmpty(duration)) {
            return 0;
        }
        return (long) Double.parseDouble(duration);
    }

    /**
     * 获取比特率
     *
     * @param mediaInformation mediaInformation
     */
    private long getBitrate(MediaInformation mediaInformation) {
        // 源码中标的单位是media bitrate in kb/s。但是比实际比特率大了1000倍，感觉是byte没转成kb
        long bitrate = parseLong(mediaInformation.getBitrate()) / 1024;
//        Logger.d("getBitrate by kit...  bitrate=" + bitrate);
        if (bitrate < 0) {
            bitrate = getCalculateBitrate(mediaInformation);
        }
        return bitrate;
    }

    private long getBitrateByStream(MediaInformation mediaInformation, StreamInformation stream) {
        long bitrate = parseLong(stream.getBitrate()) / 1024;
//        Logger.d("getBitrateByStream by kit...  bitrate=" + bitrate + ", fileSize=" + mediaInformation.getSize() + ", duration=" + getDuration(mediaInformation));
        if (bitrate < 0 || "mpg".equalsIgnoreCase(stream.getCodec())) {
            bitrate = getBitrate(mediaInformation);
        }
        return bitrate;
    }

    private long getCalculateBitrate(MediaInformation mediaInformation) {
        // 文件大小byte转kb
        long sizeKb = parseLong(mediaInformation.getSize()) / 1024;
        // 时长单位
        long durationSecond = getDuration(mediaInformation);
        // 比特率单位。kbps
        //        Logger.d("getCalculateBitrate by kit...  bitrate=" + bitrate + ", sizeKb=" + sizeKb + ", duration=" + durationSecond);
        return sizeKb * 8 / durationSecond;
    }

    public long getFrameRate(StreamInformation stream) {
        try {
            String[] split = stream.getRealFrameRate().split("/");
            return (long) (Double.parseDouble(split[0]) / Double.parseDouble(split[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getRotation(StreamInformation stream) {
        try {
            JSONArray jsonArray = stream.getAllProperties().optJSONArray("side_data_list");
            if (jsonArray != null) {
                return jsonArray.getJSONObject(0).getInt("rotation");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception ignored) {

        }
        return -1;
    }

    private long parseLong(String s) {
        try {
            return Long.parseLong(s);
        } catch (Exception ignored) {

        }
        return -1;
    }

}
