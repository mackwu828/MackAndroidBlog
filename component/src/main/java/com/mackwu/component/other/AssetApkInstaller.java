package com.mackwu.component.other;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import androidx.core.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Administrator on 2016/8/12.
 */
public class AssetApkInstaller {

    private static AssetApkInstaller assetApkInstaller;
    private ExecutorService executorService = Executors.newFixedThreadPool(20);// 线程池

    public static AssetApkInstaller getInstance() {
        if (assetApkInstaller == null) {
            assetApkInstaller = new AssetApkInstaller();
        }
        return assetApkInstaller;
    }

    public void installXWalk(Context context) {
        install(context, "xwalk");
    }

    public void install(Context context, String fileName) {
        Log.d("TAG", "installApk...");
        Runnable runnable = () -> {
            try {
                InputStream stream = context.getAssets().open(fileName + ".aac");
                byte[] buf = new byte[1024];
                int len;
                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) +
                        File.separator +
                        "Android" +
                        File.separator +
                        "data" +
                        File.separator +
                        context.getApplicationContext().getPackageName() +
                        File.separator +
                        "files" +
                        File.separator +
                        "apk";
                File file = new File(path, fileName + ".apk");
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("TAG", "exists... " + file.exists());
                Log.d("TAG", "isDirectory... " + file.isDirectory());
                Log.d("TAG", "isFile... " + file.isFile());
                Log.d("TAG", "length... " + file.length());
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                while ((len = stream.read(buf)) != -1) {
                    fileOutputStream.write(buf, 0, len);
                    fileOutputStream.flush();
                }
                fileOutputStream.close();
                stream.close();
                Log.d("TAG", "start... ");
                Log.d("TAG", "start... " + file.getAbsolutePath());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                if (Build.VERSION.SDK_INT >= 24) {
                    intent.setDataAndType(FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file), "application/vnd.android.package-archive");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                } else {
                    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                }
                context.startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        executorService.submit(runnable);
    }

}
