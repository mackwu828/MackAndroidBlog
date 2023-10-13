package com.mackwu.component;

import android.app.Application;

import com.mackwu.component.func.LocalDataManager;
import com.mackwu.component.util.WebViewUtil;


/**
 * @author MackWu
 * @since 2020/12/7 16:50
 */
public class ComponentApp extends Application {

    public static final String TAG = ComponentApp.class.getSimpleName();
    private static ComponentApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LocalDataManager.getInstance().init(this);
    }

    public static ComponentApp getInstance() {
        return instance;
    }

}
