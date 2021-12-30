package com.mackwu.component.core;

import android.os.RemoteException;

import com.mackwu.component.IWebInterface;

/**
 * ===================================================
 * Created by MackWu on 2021/11/12 17:02
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class WebProcessManager {

    private static WebProcessManager instance;
    private IWebInterface iWebInterface;

    private WebProcessManager() {
    }

    public static WebProcessManager getInstance() {
        if (instance == null) {
            instance = new WebProcessManager();
        }
        return instance;
    }

    public void setWebInterface(IWebInterface iWebInterface) {
        this.iWebInterface = iWebInterface;
    }

    public void showAcrossProcess() {
        try {
            if (iWebInterface != null) iWebInterface.show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void hideAcrossProcess() {
        try {
            if (iWebInterface != null) iWebInterface.hide();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void loadUrlAcrossProcess() {
        try {
            if (iWebInterface != null) iWebInterface.loadUrl();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
