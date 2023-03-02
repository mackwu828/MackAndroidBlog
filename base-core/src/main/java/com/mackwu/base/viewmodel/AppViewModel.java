package com.mackwu.base.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

/**
 * @author MackWu
 * @since 2021/3/15 16:17
 */
public class AppViewModel extends BaseViewModel {

    private static AppViewModel instance;
    protected Application application;

    public static AppViewModel getInstance(@NonNull Application application) {
        if (instance == null) {
            instance = new AppViewModel(application);
        }
        return instance;
    }

    public AppViewModel(@NonNull Application application) {
        this.application = application;
    }

}
