package com.mackwu.video_compress.frame;

import com.arthenica.ffmpegkit.FFmpegKit;
import com.arthenica.ffmpegkit.FFmpegSession;
import com.mackwu.video_compress.util.FileUtil;
import com.mackwu.video_compress.util.Logger;

import java.io.File;

/**
 * @author MackWu
 * @since 2023/2/2 18:08
 */
public interface VideoFrameGenerator {

    /**
     * 获取视频首帧
     */
    void generateFirstFrame();

    class Generator implements VideoFrameGenerator {
        private final String filePath;

        public Generator(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public void generateFirstFrame() {
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

}
