package com.mackwu.component.core.backup;

import android.content.Context;

import com.mackwu.base.util.LogUtil;
import com.mackwu.component.util.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Locale;

/**
 * ===================================================
 * Created by MackWu on 2022/1/20 11:08
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class BackupManager {

    private static BackupManager instance;
    private Context context;
    private String backupDirPath;
    private File backupDir;

    private BackupManager() {
    }

    public static BackupManager getInstance() {
        if (instance == null) {
            instance = new BackupManager();
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        this.backupDirPath = SdcardUtil.getExternalSdCardPath(context) + "/component";
        this.backupDir = new File(backupDirPath);
        if (!backupDir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            backupDir.mkdirs();
        }
    }

    public void backupDatabase() {
        LogUtil.d("backupDatabase...");
        long startTime = System.currentTimeMillis();

        // localDatabaseFile
        File localDatabaseFile = context.getDatabasePath("component.db");
        LogUtil.d("localDatabaseFile==" + localDatabaseFile.getAbsolutePath());

        // backupDatabaseFile
        File backupDatabaseFile = new File(backupDir, "component.db");
        LogUtil.d("backupDatabaseFile==" + backupDatabaseFile.getAbsolutePath());

        // copy
        long size = copy(localDatabaseFile, backupDatabaseFile);
        LogUtil.d("copy final...  database size==" + bytesToStr(size));
        LogUtil.d("backupDatabase completed...  costTime==" + (System.currentTimeMillis() - startTime));
    }

    public void restoreDatabase() {
        LogUtil.d("restoreDatabase...");
        long startTime = System.currentTimeMillis();

        // backupDatabaseFile
        File backupDatabaseFile = new File(backupDir, "component.db");
        LogUtil.d("backupDatabaseFile==" + backupDatabaseFile.getAbsolutePath());

        // localDatabaseFile
        String localDatabaseDirPath = context.getFilesDir().getAbsolutePath().replace("files", "databases");
        File localDatabaseDir = new File(localDatabaseDirPath);
        if (!localDatabaseDir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            localDatabaseDir.mkdirs();
        }
        File localDatabaseFile = new File(localDatabaseDir, "component.db");
        LogUtil.d("localDatabaseFile==" + localDatabaseFile.getAbsolutePath());

        // copy
        long size = copy(backupDatabaseFile, localDatabaseFile);
        LogUtil.d("copy final...  database size==" + bytesToStr(size));

        LogUtil.d("restoreDatabase  completed...  costTime==" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 拷贝文件。
     *
     * @param sourceFile 源文件
     * @param destFile   目标文件
     */
    private long copy(File sourceFile, File destFile) {
        FileChannel src = null;
        FileChannel dst = null;
        try {
            src = new FileInputStream(sourceFile).getChannel();
            dst = new FileOutputStream(destFile).getChannel();
            dst.transferFrom(src, 0, src.size());
            return destFile.length();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(src, dst);
        }
        return -1;
    }

    /**
     * 字节转化字符串
     * 1GB=102mb
     * 1MB=1024kb
     * 1Kb=1024bytes
     */
    private String bytesToStr(long space) {
        if (space < 1024) {
            return space + "bytes";
        } else if (space < 1024 * 1024) {
            return String.format(Locale.getDefault(), "%.2f", space / 1024f) + "KB";
        } else if (space < 1024 * 1024 * 1024) {
            return String.format(Locale.getDefault(), "%.2f", space / 1024f / 1024) + "MB";
        } else {
            return String.format(Locale.getDefault(), "%.2f", space / 1024f / 1024 / 1024) + "GB";
        }
    }

}
