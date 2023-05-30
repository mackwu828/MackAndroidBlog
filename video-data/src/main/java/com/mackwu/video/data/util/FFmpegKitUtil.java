package com.mackwu.video.data.util;

import android.text.TextUtils;

import com.arthenica.ffmpegkit.MediaInformation;
import com.arthenica.ffmpegkit.StreamInformation;

import org.json.JSONArray;

import java.util.List;

/**
 * @author MackWu
 * @since 2023/3/10 16:29
 */
public class FFmpegKitUtil {

    /**
     * 获取视频流信息
     *
     * @param mediaInformation mediaInformation
     * @return StreamInformation
     */
    public static StreamInformation getVideoStream(MediaInformation mediaInformation) {
        List<StreamInformation> streams = mediaInformation.getStreams();
        for (StreamInformation stream : streams) {
            if ("video".equalsIgnoreCase(stream.getType())) {
                return stream;
            }
        }
        return null;
    }

    /**
     * 获取音频流信息
     *
     * @param mediaInformation mediaInformation
     * @return StreamInformation
     */
    public static StreamInformation getAudioStream(MediaInformation mediaInformation) {
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
    public static long getDuration(MediaInformation mediaInformation) {
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
    public static long getBitrate(MediaInformation mediaInformation) {
        // 源码中标的单位是media bitrate in kb/s。但是比实际比特率大了1000倍，感觉是byte没转成kb
        long bitrate = parseLong(mediaInformation.getBitrate()) / 1024;
//        Logger.d("getBitrate by kit...  bitrate=" + bitrate);
        if (bitrate < 0) {
            bitrate = getCalculateBitrate(mediaInformation);
        }
        return bitrate;
    }

    /**
     * 根据特点视频流获取比特率
     *
     * @param mediaInformation mediaInformation
     */
    public static long getBitrateByStream(MediaInformation mediaInformation, StreamInformation stream) {
        long bitrate = parseLong(stream.getBitrate()) / 1024;
//        Logger.d("getBitrateByStream by kit...  bitrate=" + bitrate + ", fileSize=" + mediaInformation.getSize() + ", duration=" + getDuration(mediaInformation));
        if (bitrate < 0 || "mpg".equalsIgnoreCase(stream.getCodec())) {
            bitrate = getBitrate(mediaInformation);
        }
        return bitrate;
    }

    /**
     * 根据计算获取比特率
     *
     * @param mediaInformation mediaInformation
     */
    public static long getCalculateBitrate(MediaInformation mediaInformation) {
        // 文件大小byte转kb
        long sizeKb = parseLong(mediaInformation.getSize()) / 1024;
        // 时长单位
        long durationSecond = getDuration(mediaInformation);
        // 比特率单位。kbps
        //        Logger.d("getCalculateBitrate by kit...  bitrate=" + bitrate + ", sizeKb=" + sizeKb + ", duration=" + durationSecond);
        return sizeKb * 8 / durationSecond;
    }

    /**
     * 获取帧率
     * @param stream StreamInformation
     */
    public static long getFrameRate(StreamInformation stream) {
        try {
            String[] split = stream.getRealFrameRate().split("/");
            return (long) (Double.parseDouble(split[0]) / Double.parseDouble(split[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取旋转角度
     * @param stream StreamInformation
     */
    public static int getRotation(StreamInformation stream) {
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

    private static int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception ignored) {

        }
        return -1;
    }

    private static long parseLong(String s) {
        try {
            return Long.parseLong(s);
        } catch (Exception ignored) {

        }
        return -1;
    }

}
