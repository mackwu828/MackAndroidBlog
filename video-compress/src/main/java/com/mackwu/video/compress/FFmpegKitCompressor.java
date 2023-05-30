package com.mackwu.video.compress;

import com.arthenica.ffmpegkit.FFmpegKit;

/**
 * @author MackWu
 * @since 2023/2/6 17:38
 */
public class FFmpegKitCompressor implements VideoCompressor {

    String inPath;
    String outPath;
    String[] speedLevels = new String[]{"ultrafast", "superfast", "veryfast", "faster", "fast", "medium", "slow", "slower", "veryslow", "placebo"};

    public FFmpegKitCompressor(VideoCompressor.Builder builder) {
        this.inPath = builder.inPath;
        this.outPath = builder.outPath;
    }

    @Override
    public void startCompress(VideoCompressor.CompressListener compressListener) {
//            fFmpegArgs.append("-threads").append("2")
//                    .append("-profile:v").append("high")

//            String command = "-i %s" // 指定输入流
//                    + " -preset superfast" // 指定转码速率。速率越快，视频越模糊
//                    + " -max_muxing_queue_size 1024" // 增大处理缓存大小
//                    + " -vcodec libx264" // 指定视频编码
//                    + " -pix_fmt yuv420p"
//                    + " -profile:v high" // 视频压缩
//                    + " -acodec copy" // 指定音频编码
//                    + " -strict -2" // 使用aac音频编码
//                    + " -s 1920*1080" // 指定宽高
//                    ;
//            FFmpegSession session = FFmpegKit.execute(command);

        // ffmpeg -i input.mp4 -c:v libx264 -c:a copy output.mp4
        // ffmpeg -i input.mp4 -c:v libx264 -crf 24 -c:a copy output.mp4
        // ffmpeg -i input.mp4 -c:v libx265 -crf 28 output.mp4
        // -profile:v baseline -pix_fmt yuv420p


        String command = String.format(
                "-i %s -c:v libx264 -c:a copy %s",
                inPath,
                outPath);
        FFmpegKit.executeAsync(command, session -> {

        }, log -> {

        }, statistics -> {


        });
    }

    @Override
    public void cancelCompress() {

    }

}
