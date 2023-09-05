package com.mackwu.audioplayer;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

/**
 * @author MackWu
 * @since 2023/7/3 11:52
 */
public class AudioPlayer {

    //    int streamType, int sampleRateInHz, int channelConfig, int audioFormat,
//    int bufferSizeInBytes, int mode
    private int streamType;
    private int sampleRateInHz;
    private int channelConfig;
    private int audioFormat;
    private int bufferSizeInBytes;
    private int mode;
    private AudioTrack audioTrack;

    private int chunkSize;

    private void init() {
        // 流的类型。
        streamType = AudioManager.STREAM_MUSIC;
        // 采样率。
        sampleRateInHz = 32000;
        // 声道。CHANNEL_OUT_MONO单身道、CHANNEL_OUT_STEREO双声道
        channelConfig = AudioFormat.CHANNEL_OUT_MONO;
        // 格式。ENCODING_PCM_8BIT：8位、ENCODING_PCM_16BIT：16位，现在基本都是16位。
        audioFormat = AudioFormat.ENCODING_PCM_16BIT;
        // 缓冲区大小。
        bufferSizeInBytes = AudioTrack.getMinBufferSize(
                sampleRateInHz,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT);
        // 模式。MODE_STREAM流类型、MODE1_STATIC？
        mode = AudioTrack.MODE_STREAM;
        AudioTrack audioTrack = new AudioTrack(
                streamType,
                sampleRateInHz,
                channelConfig,
                audioFormat,
                bufferSizeInBytes,
                mode
        );

        // 1帧字节数=声道*格式字节数。如单声道16位就是2个字节，双声道16位就是4个字节
        // n采样率16位50ms下每次读取的字节数。为啥
        chunkSize = (int) (sampleRateInHz * 2 * 0.05);
    }

    public void test() {
        audioTrack.play();


        byte[] buffer = new byte[chunkSize];

    }

}
