package com.mackwu.component.ui.viewmodel;

import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;

/**
 * ===================================================
 * Created by MackWu on 2021/10/12 15:56
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class HandlerViewModel extends BaseViewModel {

    public void getData() {
        LogUtil.d("getData...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 60 * 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
