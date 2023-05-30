package com.mackwu.component.ui.viewmodel;

import android.os.Environment;

import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.storage.util.Logger;
import com.mackwu.video.compress.VideoCompressor;
import com.mackwu.video.data.VideoDataRetriever;

/**
 * @author MackWu
 * @since 2023/3/10 16:21
 */
public class VideoViewModel extends BaseViewModel {


    public void getVideoData(){
        String filePath = "";
        VideoDataRetriever videoDataRetriever = new VideoDataRetriever(filePath);
        videoDataRetriever.getVideoData();
    }

    public void getVideoFirstFrame(){
        String filePath = "";
        VideoDataRetriever videoDataRetriever = new VideoDataRetriever(filePath);
        videoDataRetriever.getVideoFirstFrame();
    }

    public void compressVideo(){
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getPath();
        String inPath = filePath + "video.mp4";
        String outPath = filePath + "xxx.mp4";
        Logger.d("filePath=" + filePath);
        VideoCompressor videoCompressor = new VideoCompressor.Builder()
                .inPath(inPath)
                .outPath(outPath)
                .build();
        videoCompressor.startCompress(new VideoCompressor.CompressListener() {
            @Override
            public void onCompressProgressChanged(int progress) {

            }

            @Override
            public void onCompressComplete() {

            }

            @Override
            public void onCompressFail() {

            }
        });
    }
}
