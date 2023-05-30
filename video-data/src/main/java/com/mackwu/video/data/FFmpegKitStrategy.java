package com.mackwu.video.data;

import com.arthenica.ffmpegkit.FFmpegKit;
import com.arthenica.ffmpegkit.FFmpegSession;
import com.arthenica.ffmpegkit.FFprobeKit;
import com.arthenica.ffmpegkit.MediaInformation;
import com.arthenica.ffmpegkit.MediaInformationSession;
import com.arthenica.ffmpegkit.ReturnCode;
import com.arthenica.ffmpegkit.StreamInformation;
import com.mackwu.video.data.bean.VideoData;
import com.mackwu.video.data.util.FFmpegKitUtil;
import com.mackwu.video.data.util.FileUtil;
import com.mackwu.video.data.util.Logger;

import java.io.File;

/**
 * @author MackWu
 * @since 2023/2/1 15:34
 */
public class FFmpegKitStrategy implements VideoDataStrategy {

    private final String filePath;

    public FFmpegKitStrategy(String filePath) {
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
            videoData.setBitrate(FFmpegKitUtil.getBitrate(mediaInformation));
            videoData.setDuration(FFmpegKitUtil.getDuration(mediaInformation));
            // videoStream
            StreamInformation videoStream = FFmpegKitUtil.getVideoStream(mediaInformation);
            if (videoStream != null) {
                videoData.setVideoWidth((int) videoStream.getWidth().longValue());
                videoData.setVideoHeight((int) videoStream.getHeight().longValue());
                videoData.setVideoCodec(videoStream.getCodec());
                videoData.setVideoBitrate(FFmpegKitUtil.getBitrateByStream(mediaInformation, videoStream));
                videoData.setVideoFormat(videoStream.getFormat());
                videoData.setVideoFrameRate(FFmpegKitUtil.getFrameRate(videoStream));
                videoData.setVideoRotation(FFmpegKitUtil.getRotation(videoStream));
            }
            // audioStream
            StreamInformation audioStream = FFmpegKitUtil.getAudioStream(mediaInformation);
            if (audioStream != null) {
                videoData.setAudioCodec(audioStream.getCodec());
                videoData.setAudioBitrate(FFmpegKitUtil.getBitrateByStream(mediaInformation, audioStream));
            }
            Logger.d("getVideoData by kit completed...  " + videoData);
            return videoData;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void getVideoFirstFrame() {
        // 将文件名拼上引号，防止ffmpeg命令文件名带空格时报错。
        String fileExtension = FileUtil.getFileExtension(filePath);
        String fileNameNoExtension = FileUtil.getFileNameNoExtension(filePath);
        String dirPath = FileUtil.getDirPath(filePath);
        String sourcePath = dirPath + "\"" + fileNameNoExtension + "\"." + fileExtension;
        String thumbnailPath = dirPath + "/\"" + fileNameNoExtension + "\".jpg";
        String command = String.format("-i %s -ss 00:00:01 -vframes 1 %s", sourcePath, thumbnailPath);
        Logger.d("generateFirstFrame...  command=" + command);
        FFmpegSession session = FFmpegKit.execute(command);
//            String failStackTrace = session.getFailStackTrace();
//            Logger.d("failStackTrace=" + failStackTrace);
        File thumbnailFile = new File(thumbnailPath.replace("\"", ""));
        Logger.d("thumbnailFile=" + thumbnailFile.exists());
        Logger.d("generateFirstFrame completed...");
    }


}
