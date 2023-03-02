package com.mackwu.storage.scan;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.mackwu.storage.bean.StorageScanParam;
import com.mackwu.storage.util.ByteUtil;
import com.mackwu.storage.util.UriUtil;
import com.mackwu.storage.util.Logger;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

/**
 * @author MackWu
 * @since 2023/1/31 19:03
 */
public class StorageScanService extends Service {

    StorageScanParam param;
    boolean isScanCancelled;
    // 过滤隐藏文件
    private final FileFilter hiddenFilter = pathname -> !pathname.isHidden();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IStorageScanService.Stub() {
            @Override
            public void startScan(StorageScanParam p, IStorageScanListener listener) throws RemoteException {
                new Thread(() -> {
                    try {
                        Logger.d("startScan in service...  pid=" + android.os.Process.myPid());
                        isScanCancelled = false;
                        param = p;
                        if (listener != null) {
                            listener.onScanStart();
                        }
                        onScan(new File(param.getRootPath()), listener);
                        if (listener != null) {
                            listener.onScanComplete();
                        }
                        Logger.d("startScan in service completed...  pid=" + android.os.Process.myPid());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }

            @Override
            public void cancelScan() throws RemoteException {
                isScanCancelled = true;
            }
        };
//        return null;
    }

    /**
     * 递归扫描
     *
     * @param file         file
     * @param scanListener scanListener
     */
    private void onScan(File file, IStorageScanListener scanListener) throws RemoteException {
        String filePath = file.getPath();
        String fileName = file.getName();
        // 扫描取消
        if (isScanCancelled) {
            Logger.d("scan...  scan is cancelled");
            return;
        }
        // 忽略对应路径
        List<String> exceptPaths = param.getExceptPaths();
        if (exceptPaths != null && exceptPaths.contains(filePath)) {
            Logger.d("scan...  file is ignored, path=" + filePath);
            return;
        }
        // 文件是否存在
        if (!file.exists()) {
            Logger.d("scan...  file is not exist, path=" + filePath);
            return;
        }
        // 是文件夹。遍历所有文件
        if (file.isDirectory()) {
            File[] files = file.listFiles(hiddenFilter);
            if (files != null && files.length > 0) {
                for (File f : files) {
                    onScan(f, scanListener);
                }
            }
        }
        // 是文件
        else {
            // 文件长度
            long fileLength = file.length();
            // 文件大小超过最大值忽略
            if (fileLength > param.getFileMaxSize()) {
                Logger.d("scan...  file is too large, path=" + filePath + ", size=" + ByteUtil.bytesToStr(fileLength));
                return;
            }
            // 忽略缩略图
            if (fileName.contains("_thumbnail")) {
                return;
            }
            // 扫描进度回调
            Logger.d("scan...  result path=" + filePath);
            scanListener.onScanProgressChanged(filePath);
        }
    }

}
