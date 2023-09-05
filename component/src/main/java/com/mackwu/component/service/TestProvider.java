package com.mackwu.component.service;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.base.util.Logger;
import com.mackwu.component.util.AsyncToSyncTask;

/**
 * @author MackWu
 * @since 2023/7/26 10:51
 */
public class TestProvider extends ContentProvider {

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String[] columnNames = new String[]{"testString"};
        MatrixCursor cursor = new MatrixCursor(columnNames);
        Logger.d("testString start");
        AsyncToSyncTask asyncToSyncTask = new AsyncToSyncTask();
        asyncToSyncTask.start(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cursor.addRow(new String[]{"Test222222222"});
        });
        Logger.d("testString end");
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
