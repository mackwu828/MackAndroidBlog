package com.mackwu.component.func.transport;

import androidx.annotation.NonNull;

import com.mackwu.component.util.IOUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author MackWu
 * @since 2022/6/17 17:25
 */
public class ProgressFileTransporter {

    //    Transporter.TransportHandle transportHandle;
    int progress;
    int lastProgress;

//    public ProgressFileTransporter(Transporter.TransportHandle transportHandle) {
//        this.transportHandle = transportHandle;
//    }

    public interface OnProgressChangedListener {
        void onProgressChanged(int progress);
    }

    public void transfer(String sourcePath, String destPath, @NonNull OnProgressChangedListener progressChangedListener) {
        FileChannel src = null;
        FileChannel dst = null;
        try {
            src = new FileInputStream(sourcePath).getChannel();
            dst = new FileOutputStream(destPath).getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
//            ByteBuffer buffer = ByteBuffer.allocate(1024 * 4);
            long size = src.size();
            long transferSize = 0;
            progress = 0;
            while (src.read(buffer) != -1) {
//                if (transportHandle.isTransportCancel()) {
//                    transportHandle.onTransportCancel(destPath);
////                    Logger.d("transfer canceled...  transferSize: " + transferSize + ", progress: " + progress);
//                    break;
//                }
                buffer.flip();
                transferSize += dst.write(buffer);
                progress = (int) (transferSize * 100 / size);
                if (progress >= 100) {
                    progress = 100;
                }
//                Logger.d("transfer...  transferSize: " + transferSize + ", progress: " + progress);
                if (progress > lastProgress) {
                    progressChangedListener.onProgressChanged(progress);
                }
                buffer.clear();
                lastProgress = progress;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(src, dst);
        }
    }

}
