package com.mackwu.component.ui.viewmodel;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.AppViewModel;
import com.mackwu.component.util.ByteUtil;

import java.io.File;

/**
 * @author MackWu
 * @since 2023/3/10 18:03
 */
public class ImageCompressViewModel extends AppViewModel {

    public ImageCompressViewModel(@NonNull Application application) {
        super(application);
    }

    public void fileToBitmap() {
        File sourceFile = new File(application.getExternalFilesDir(null), "big.jpg");
        Bitmap sourceBitmap = BitmapFactory.decodeFile(sourceFile.getPath());
        Logger.d(ByteUtil.getByteCount(sourceBitmap));
    }

}
