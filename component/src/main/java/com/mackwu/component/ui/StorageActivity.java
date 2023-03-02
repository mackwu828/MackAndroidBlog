package com.mackwu.component.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.documentfile.provider.DocumentFile;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityStorageBinding;
import com.mackwu.storage.StorageManager;
import com.mackwu.storage.bean.Storage;
import com.mackwu.storage.scan.StorageScanListener;
import com.mackwu.storage.scan.StorageScanner;
import com.mackwu.storage.util.Logger;

import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2022/4/24 16:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class StorageActivity extends BaseActivity<BaseViewModel, ActivityStorageBinding> {

    @SuppressLint("SetTextI18n")
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        // bindStorageObserver
        StorageManager.getInstance().bindStorageObserver(this);
        Logger.d("initView...  main pid=" + android.os.Process.myPid());

        binding.btnStartScan.setOnClickListener(v -> {
            List<Storage> mountedExternalStorages = StorageManager.getInstance().getMountedExternalStorages(this);
            if (mountedExternalStorages == null || mountedExternalStorages.isEmpty()) {
                return;
            }
            Storage storage = mountedExternalStorages.get(0);
            startScan(storage.getPath());
        });

        binding.btnOpenDocument.setOnClickListener(v-> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            startActivity(intent);
        });
        binding.btnOpenDocumentTree.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
            startActivityForResult(intent, 0x01);
        });
    }

    private void startScan(String path){
        StorageScanner storageScanner = new StorageScanner.Builder(this)
                .rootPath(path)
                .build();
        storageScanner.startScan(new StorageScanListener() {
            @Override
            public void onScanStart() {

            }

            @Override
            public void onScanProgressChanged(String path) {
            }

            @Override
            public void onScanComplete() {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x01) {
            if (data != null) {
                Uri uri = data.getData();
                Logger.d("onActivityResult...  " + uri.toString()
                        + ", authority=" + uri.getAuthority()
                        + ", path=" + uri.getPath()
                );
                DocumentFile documentFile = DocumentFile.fromTreeUri(this, uri);
                if (documentFile != null) {
                    DocumentFile[] documentFiles = documentFile.listFiles();
                    for (DocumentFile file : documentFiles) {
                        Uri subUri = file.getUri();
                        Logger.d("" + subUri
                                + ", authority=" + subUri.getAuthority()
                                + ", path=" + subUri.getPath()
                        );
                    }
                }
            }
        }
    }
}
