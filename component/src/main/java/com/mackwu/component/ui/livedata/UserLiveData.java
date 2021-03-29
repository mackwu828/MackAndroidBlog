package com.mackwu.component.ui.livedata;

import androidx.lifecycle.MutableLiveData;

import com.mackwu.component.bean.User;
import com.mackwu.base.util.LogUtil;

/**
 * ===================================================
 * Created by MackWu on 2020/7/8 19:25
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class UserLiveData extends MutableLiveData<User> {

    private static UserLiveData instance;

    private UserLiveData() {
    }

    public static UserLiveData getInstance() {
        if (instance == null) {
            instance = new UserLiveData();
        }
        return instance;
    }

}
