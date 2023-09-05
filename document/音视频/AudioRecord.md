
AudioRecord的初始化？


## AudioRecord的初始化？
```
    public AudioRecord(int audioSource, int sampleRateInHz, int channelConfig, int audioFormat, int bufferSizeInBytes)
```

## AudioRecord的初始化参数介绍？
- audioSource音频源
```
`MediaRecorder.AudioSource.DEFAULT` 默认声音。
`MediaRecorder.AudioSource.VOICE_RECOGNITION` 语音识别。
`MediaRecorder.AudioSource.MIC` 麦克风声音。
`MediaRecorder.AudioSource.VOICE_UPLINK` 通话上行声音。
```

- sampleRateInHz采样率
每秒钟能够采样的次数，采样率越高，音质越高。
44100Hz保证可以在所以设备上使用、22050、16000、11025适用于部分设备。要采集低质量的音频也可以使用4000、8000等低采样率。

- channelConfig声道
```
`AudioFormat.CHANNEL_IN_MONO` 单声道。录制用 IN 播放用 OUT。兼容所有android手机。
`AudioFormat.CHANNEL_OUT_STEREO` 双声道。录制用 IN 播放用 OUT。
```

- audioFormat位宽
```
AudioFormat.ENCODING_PCM_16BIT 一般的手机可能只支持16位PCM编码，如果设置为其他的值会报错。
```

- bufferSizeInBytes采集数据缓冲区大小
```
数据缓冲区大小不能低于一帧音频的大小, 一帧音频大小与音频采样时间有关，一般取 2.5ms~120ms 之间
int bufferSizeInBytes = 采样率 x 位宽 x 采样时间 x 通道数 
int bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat)
```
