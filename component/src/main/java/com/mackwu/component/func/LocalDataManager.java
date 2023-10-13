package com.mackwu.component.func;

import android.content.Context;
import android.graphics.Matrix;
import android.text.TextUtils;

import com.getkeepsafe.relinker.ReLinker;
import com.mackwu.image.scale.util.MatrixUtil;
import com.tencent.mmkv.MMKV;

/**
 * @author MackWu
 * @since 2023/9/28 14:58
 */
public class LocalDataManager {

    private static LocalDataManager instance;
    private MMKV kv;

    private LocalDataManager() {

    }

    public static LocalDataManager getInstance() {
        if (instance == null) {
            instance = new LocalDataManager();
        }
        return instance;
    }

    public void init(Context context) {
        MMKV.initialize(context.getFilesDir().getAbsolutePath() + "/mmkv",
                libName -> ReLinker.loadLibrary(context, libName));
        kv = MMKV.defaultMMKV();
    }

    public void setScaleMatrix(Matrix matrix) {
        kv.encode("scaleMatrix", MatrixUtil.matrixToString(matrix));
    }

    public Matrix getScaleMatrix() {
        String s = kv.decodeString("scaleMatrix");
        return TextUtils.isEmpty(s) ? null : MatrixUtil.stringToMatrix(s);
    }

    public void setScaleVerticalMatrix(Matrix matrix) {
        kv.encode("scaleVerticalMatrix", MatrixUtil.matrixToString(matrix));
    }

    public Matrix getScaleVerticalMatrix() {
        String s = kv.decodeString("scaleVerticalMatrix");
        return TextUtils.isEmpty(s) ? null : MatrixUtil.stringToMatrix(s);
    }

}
